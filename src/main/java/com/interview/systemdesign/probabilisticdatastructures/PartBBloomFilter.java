package com.interview.systemdesign.probabilisticdatastructures;

/*
    Category: Medium, VVImp
    A Bloom Filter is a space-efficient probabilistic data structure designed to test
    whether an element is a member of a set. It allows for false positives (it might
    incorrectly say an element is present when it's not) but never false negatives
    (if it says an element is not present, it definitely isn't). The benefit is its
    very low memory usage, making it ideal for use cases where space is a concern and
    some margin of error is acceptable.

    How Bloom Filter Works:
    A Bloom Filter consists of:
    1. A bit array of size m, all initialized to 0.
    2. k independent hash functions that each map an element to one of the m array positions.

    Insertion:
    For each element, we pass it through the k hash functions. The hash functions compute
    k array positions (indices), and we set the corresponding positions in the bit array to 1.

    Query:
    To check if an element is possibly in the set, we pass it through the same k hash functions.
    If all k positions in the bit array are set to 1, the element may be in the set (with a small
    probability of false positive). If any one of the positions is 0, the element is definitely not in the set.

    Bloom Filter Characteristics:
    - False Positives: There’s a chance that a Bloom filter might say an element is in the set when it's not.
    - No False Negatives: If the Bloom filter says the element is not in the set, it's definitely not in the set.
    - Memory-Efficient: It uses much less space than other data structures like hash sets.

    Applications of Bloom Filters:
    - Web Caching: To quickly check if an item is in the cache before performing a more expensive lookup.
    - Databases: To check whether a particular key exists in a database table, reducing expensive disk lookups.
    - Distributed Systems: In distributed databases like BigTable, Bloom filters help avoid unnecessary lookups.
    - Networking: Used in routers to efficiently check if a packet is part of an ongoing connection.
    - Spell Checkers: Quickly checking if a word is part of the dictionary.

    Explanation of the Code:
    - BitSet bitSet: A bit array (size m) that tracks the presence of elements.
    - hashFunctions: An array of hash functions used to determine positions in the bit array for each element.
    - add(T element): Adds an element to the filter by setting the corresponding bit positions using all k hash functions.
    - mightContain(T element): Checks if the element is possibly in the filter by checking the bits for all k positions.
      If all positions are 1, the element may be in the set, otherwise, it’s definitely not.

    Advantages of Using Bloom Filters:
    - Space-efficient: Uses significantly less memory compared to traditional data structures like sets or hash maps.
    - No false negatives: Always reliable when saying an element is not in the set.

    Disadvantages:
    - False positives: There is a small chance it will indicate an element is present when it is not.
    - Cannot delete elements: Once an element is added, it cannot be removed without affecting the integrity of other elements.

    Parameters for Optimizing Bloom Filters:
    - Size of Bit Array (m): More bits mean a lower chance of false positives but require more memory.
    - Number of Hash Functions (k): More hash functions increase accuracy but also require more computation time.
      The ideal number of hash functions k and bit array size m depends on the number of elements n you expect
      to store and the acceptable false-positive rate p. The optimal k is approximately k = (m/n) * ln(2).

    Real-World Example:
    In databases like Cassandra, a Bloom filter is used to quickly check if a partition key exists in a table
    before attempting to access disk. If the filter says a key is not present, a disk lookup is avoided, improving performance.

    This combination of space efficiency and quick lookups makes Bloom filters an excellent choice in systems
    that deal with large datasets.
 */
import java.util.BitSet;
import java.util.function.Function;

public class PartBBloomFilter<T> {
    private final BitSet bitSet;
    private final int bitArraySize;
    private final Function<T, Integer>[] hashFunctions;

    @SafeVarargs
    public PartBBloomFilter(int bitArraySize, Function<T, Integer>... hashFunctions) {
        this.bitArraySize = bitArraySize;
        this.hashFunctions = hashFunctions;
        this.bitSet = new BitSet(bitArraySize);
    }

    public void add(T element) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element) % bitArraySize);
            bitSet.set(index);
        }
    }

    public boolean mightContain(T element) {
        for (Function<T, Integer> hashFunction : hashFunctions) {
            int index = Math.abs(hashFunction.apply(element) % bitArraySize);
            if (!bitSet.get(index)) {
                return false; // Definitely not in the set
            }
        }
        return true; // Might be in the set (false positive possible)
    }

    public static void main(String[] args) {
        // Example usage of BloomFilter with 3 hash functions
        PartBBloomFilter<String> partBBloomFilter = new PartBBloomFilter<>(1000,
                s -> s.hashCode(),
                s -> s.hashCode() * 31,
                s -> s.hashCode() * 17);

        partBBloomFilter.add("hello");
        partBBloomFilter.add("world");

        System.out.println(partBBloomFilter.mightContain("hello")); // True
        System.out.println(partBBloomFilter.mightContain("world")); // True
        System.out.println(partBBloomFilter.mightContain("openai")); // False (no false negatives)
    }
}
