package com.interview.systemdesign.lowleveldesign;

/*
 * Date: 12/10/2024
 * Author: Your Name
 * Difficulty: Medium
 * Company: Any Company
 * Reference: https://www.geeksforgeeks.org/multilevel-cache-organisation/?ref=rp
 * Companies: Google, Amazon, Facebook
 *
 * Overview:
 * A multi-level cache system is designed to store frequently accessed data closer to the application, minimizing
 * the time it takes to retrieve data. The system typically includes various cache layers (e.g., L1, L2, L3) to
 * store data based on access frequency and data size.
 *
 * Low-Level Design for Multi-Level Cache Organization:
 *
 * Core Requirements:
 * - Caching Layers: Implement multiple caching layers to store data at different levels (e.g., in-memory, distributed).
 * - Data Eviction: Implement eviction policies (e.g., LRU, FIFO) for cache management.
 * - Cache Invalidation: Ensure that the cache is updated when the underlying data changes.
 * - Performance Monitoring: Monitor cache hit/miss ratios and latencies.
 * - Scalability: Allow scaling of cache layers independently.
 * - Thread Safety: Ensure that the cache is thread-safe for concurrent access.
 *
 * Components:
 * 1. Cache Layer Interface: Defines methods for interaction with various cache layers.
 *    - get(key): Retrieves data from the cache.
 *    - put(key, value): Stores data in the cache.
 *    - invalidate(key): Invalidates a specific cache entry.
 *    - clear(): Clears the entire cache.
 *
 * 2. In-Memory Cache (L1):
 *    - Implementation: Uses a hashmap for fast access.
 *    - Eviction Policy: LRU (Least Recently Used).
 *    - Classes: InMemoryCache, LRUCache
 *
 * 3. Distributed Cache (L2):
 *    - Implementation: Uses a distributed key-value store (e.g., Redis, Memcached).
 *    - Eviction Policy: TTL (Time-to-Live).
 *    - Classes: DistributedCache, RedisCacheClient
 *
 * 4. Persistent Cache (L3):
 *    - Implementation: Uses a database or file storage for persistent caching.
 *    - Eviction Policy: Size-based limit.
 *    - Classes: PersistentCache, DatabaseCacheClient
 *
 * 5. Cache Manager:
 *    - Responsibilities:
 *      - Coordinates between cache layers.
 *      - Determines which layer to query first.
 *    - Classes: CacheManager
 *    - Functions:
 *      - getData(key): Checks L1, then L2, and finally L3.
 *      - setData(key, value): Sets data in L1 and updates L2/L3 if necessary.
 *      - invalidateData(key): Invalidates data in all layers.
 *
 * System Design:
 * 1. Database Design:
 *    - Data Table: Data
 *    - Columns: id, value, lastUpdated
 *
 * 2. Service Layer:
 *    - CacheService: Manages cache operations.
 *    - Functions:
 *      - getValue(key): Retrieves the value from the cache.
 *      - setValue(key, value): Sets the value in the cache.
 *      - invalidateValue(key): Invalidates the value in all cache layers.
 *
 * 3. API Design:
 *    - GET /cache/{key}: Retrieves data from the cache.
 *    - POST /cache: Sets data in the cache.
 *    - DELETE /cache/{key}: Invalidates a specific cache entry.
 *
 * Scalability Considerations:
 * - Horizontal Scaling: Allow adding more instances of the distributed cache.
 * - Partitioning: Use consistent hashing for partitioning cache data in distributed caches.
 * - Load Balancing: Use load balancers to distribute traffic evenly across cache instances.
 *
 * Security Considerations:
 * - Access Control: Implement access controls for cache data.
 * - Data Encryption: Ensure sensitive data in the cache is encrypted.
 * - Rate Limiting: Apply rate limiting on cache access to prevent abuse.
 *
 * Monitoring:
 * - Metrics: Track cache hit/miss ratios, latency, and evictions.
 * - Alerts: Set up alerts for cache performance issues.
 *
 * Conclusion:
 * A multi-level cache organization enhances data retrieval speeds and reduces the load on backend systems.
 * This design approach leverages different caching layers with appropriate eviction policies and monitoring
 * mechanisms to ensure efficient and scalable caching.
 */

import java.util.LinkedHashMap;
import java.util.Map;

import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public interface Cache {
    Object get(String key);
    void put(String key, Object value);
    void invalidate(String key);
    void clear();
}



public class InMemoryCache implements Cache {
    private final Map<String, Object> cache;
    private final int maxSize;

    public InMemoryCache(int maxSize) {
        this.maxSize = maxSize;
        this.cache = new LinkedHashMap<>(maxSize, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<String, Object> eldest) {
                return size() > maxSize;
            }
        };
    }

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    @Override
    public void invalidate(String key) {
        cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }
}

public class DistributedCache implements Cache {
    private final Jedis jedis;

    public DistributedCache(String host, int port) {
        this.jedis = new Jedis(host, port);
    }

    @Override
    public Object get(String key) {
        return jedis.get(key);
    }

    @Override
    public void put(String key, Object value) {
        jedis.set(key, value.toString());
    }

    @Override
    public void invalidate(String key) {
        jedis.del(key);
    }

    @Override
    public void clear() {
        jedis.flushDB();
    }
}

public class PersistentCache implements Cache {
    private final Connection connection;

    public PersistentCache(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Object get(String key) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT value FROM Data WHERE id = ?")) {
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
        try (PreparedStatement stmt = connection.prepareStatement("REPLACE INTO Data (id, value) VALUES (?, ?)")) {
            stmt.setString(1, key);
            stmt.setString(2, value.toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void invalidate(String key) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Data WHERE id = ?")) {
            stmt.setString(1, key);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM Data")) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


public class CacheManager {
    private final Cache inMemoryCache;
    private final Cache distributedCache;
    private final Cache persistentCache;

    public CacheManager(Cache inMemoryCache, Cache distributedCache, Cache persistentCache) {
        this.inMemoryCache = inMemoryCache;
        this.distributedCache = distributedCache;
        this.persistentCache = persistentCache;
    }

    public Object getData(String key) {
        // Try In-Memory Cache (L1)
        Object value = inMemoryCache.get(key);
        if (value != null) {
            return value;
        }
        // Try Distributed Cache (L2)
        value = distributedCache.get(key);
        if (value != null) {
            inMemoryCache.put(key, value); // Update L1
            return value;
        }
        // Try Persistent Cache (L3)
        value = persistentCache.get(key);
        if (value != null) {
            distributedCache.put(key, value); // Update L2
            inMemoryCache.put(key, value); // Update L1
        }
        return value;
    }

    public void setData(String key, Object value) {
        inMemoryCache.put(key, value); // Update L1
        distributedCache.put(key, value); // Update L2
        persistentCache.put(key, value); // Update L3
    }

    public void invalidateData(String key) {
        inMemoryCache.invalidate(key);
        distributedCache.invalidate(key);
        persistentCache.invalidate(key);
    }
}

public class MultilevelCacheOrganisationDesign {
    public static void main(String[] args) {
        // In-Memory Cache (L1)
        InMemoryCache inMemoryCache = new InMemoryCache(100);

        // Distributed Cache (L2) - Using Redis
        JedisPool jedisPool = new JedisPool("localhost", 6379);
        DistributedCache distributedCache = new DistributedCache("localhost", 6379);

        // Persistent Cache (L3) - Using a hypothetical SQL database
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_db", "user", "password");
            PersistentCache persistentCache = new PersistentCache(connection);

            // Cache Manager
            CacheManager cacheManager = new CacheManager(inMemoryCache, distributedCache, persistentCache);

            // Example operations
            String key = "exampleKey";
            String value = "exampleValue";

            // Set data
            cacheManager.setData(key, value);
            System.out.println("Data set in cache.");

            // Get data
            Object cachedValue = cacheManager.getData(key);
            System.out.println("Retrieved from cache: " + cachedValue);

            // Invalidate data
            cacheManager.invalidateData(key);
            System.out.println("Data invalidated.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            jedisPool.close(); // Close Redis connection pool if used
        }
    }
    
}
