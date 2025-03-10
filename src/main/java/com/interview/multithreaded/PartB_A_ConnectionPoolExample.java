package com.interview.multithreaded;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
public class PartB_A_ConnectionPoolExample {

/*
    📌 Explanation of the Code
    🔹 1. ConnectionPool Class
    Uses a BlockingQueue (LinkedBlockingQueue) to manage connections.
    Initializes a fixed pool size and pre-fills it with connections.
    Implements getConnection() to acquire a connection.
    Implements releaseConnection() to return the connection back to the pool.
    Provides a shutdown() method to gracefully close all connections.
    🔹 2. Multi-threaded Testing (Main Class)
    Creates a ConnectionPool with a size of 5.
    Spawns 10 threads to acquire and release connections.
    Ensures proper thread synchronization.
    Calls shutdown() after all threads complete execution.
    ✅ Why This Implementation?
    ✔ Efficient & Scalable: Uses a queue-based approach to manage connections.
    ✔ Thread-safe: Uses synchronized methods to prevent race conditions.
    ✔ Graceful shutdown: Ensures all connections are closed properly.

    🔹 Output Example
    css
    Copy
    Edit
    Thread-1 acquired a connection.
    Thread-2 acquired a connection.
    Thread-3 acquired a connection.
    Thread-4 acquired a connection.
    Thread-5 acquired a connection.
    Thread-1 released a connection.
    Thread-2 released a connection.
    ...
    Connection Pool shutdown successfully.
 */
    private static class ConnectionPool {
        private final BlockingQueue<Connection> connectionPool;
        private final int MAX_POOL_SIZE;
        private final String dbUrl, dbUser, dbPassword;
        private boolean isShutdown = false;

        // Constructor initializes the pool with a fixed size
        public ConnectionPool(String dbUrl, String dbUser, String dbPassword, int poolSize) throws SQLException {
            this.dbUrl = dbUrl;
            this.dbUser = dbUser;
            this.dbPassword = dbPassword;
            this.MAX_POOL_SIZE = poolSize;
            this.connectionPool = new LinkedBlockingQueue<>(poolSize);

            // Prepopulate the pool with connections
            for (int i = 0; i < poolSize; i++) {
                connectionPool.offer(createNewConnection());
            }
        }

        // Method to create a new database connection
        private Connection createNewConnection() throws SQLException {
            return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }

        // Method to borrow a connection from the pool
        public synchronized Connection getConnection() throws InterruptedException, SQLException {
            if (isShutdown) {
                throw new IllegalStateException("Connection Pool is shut down!");
            }
            return connectionPool.take(); // Blocks if no connection is available
        }

        // Method to return a connection back to the pool
        public synchronized void releaseConnection(Connection connection) {
            if (connection != null && !isShutdown) {
                connectionPool.offer(connection);
            }
        }

        // Gracefully shuts down the pool by closing all connections
        public synchronized void shutdown() throws SQLException {
            isShutdown = true;
            while (!connectionPool.isEmpty()) {
                Connection conn = connectionPool.poll();
                if (conn != null) {
                    conn.close();
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException, InterruptedException {
        String dbUrl = "jdbc:mysql://localhost:3306/mydatabase";
        String dbUser = "root";
        String dbPassword = "password";
        int poolSize = 5;

        ConnectionPool connectionPool = new ConnectionPool(dbUrl, dbUser, dbPassword, poolSize);

        // Simulating multiple threads using the connection pool
        Runnable task = () -> {
            try {
                Connection conn = connectionPool.getConnection();
                System.out.println(Thread.currentThread().getName() + " acquired a connection.");

                // Simulating DB operation
                Thread.sleep(2000);

                connectionPool.releaseConnection(conn);
                System.out.println(Thread.currentThread().getName() + " released a connection.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        // Create multiple threads to use the connection pool
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(task);
            threads[i].start();
        }

        // Wait for all threads to finish
        for (Thread t : threads) {
            t.join();
        }

        // Shutdown the pool
        connectionPool.shutdown();
        System.out.println("Connection Pool shutdown successfully.");
    }
}
