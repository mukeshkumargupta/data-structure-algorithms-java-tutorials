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

public class PartBBloomFilter {

    private final boolean[] bitArray; // Boolean array to store the bits
    private final int bitArraySize; // Size of the bit array
    private final int hashCount; // Number of hash functions

    /**
     * Constructor to initialize the Bloom Filter.
     *
     * @param bitArraySize The size of the bit array.
     * @param hashCount    The number of hash functions to use.
     */
    public PartBBloomFilter(int bitArraySize, int hashCount) {
        this.bitArraySize = bitArraySize;
        this.hashCount = hashCount;
        this.bitArray = new boolean[bitArraySize]; // Initialize the boolean array
    }

    /**
     * Adds an element to the Bloom Filter.
     *
     * @param element The element to add (as a String).
     */
    public void add(String element) {
        for (int i = 0; i < hashCount; i++) {
            int index = getHash(element, i) % bitArraySize; // Compute bit index
            bitArray[index] = true; // Set the bit
        }
    }

    /**
     * Checks if an element might be in the Bloom Filter.
     *
     * @param element The element to check (as a String).
     * @return True if the element might be in the set; False if definitely not.
     */
    public boolean mightContain(String element) {
        for (int i = 0; i < hashCount; i++) {
            int index = getHash(element, i) % bitArraySize; // Compute bit index
            if (!bitArray[index]) {
                return false; // Element is definitely not in the set
            }
        }
        return true; // Element might be in the set (false positive possible)
    }

    /**
     * A simple hash function that combines the element's hash code with a seed.
     *
     * @param element The element to hash (as a String).
     * @param seed    The seed to vary the hash.
     * @return A hash value for the element.
     */
    /*
        The reason for using the expression element.hashCode() ^ (seed * 31) in the getHash method is to create a more customized and varied hash, especially when using it in contexts like hash tables or distributed systems. Here's a breakdown of the key components and why they are used:

    element.hashCode():

    The hashCode() method is a standard method in Java that returns an integer hash value for an object. It provides a way to represent an object as an integer for quick comparisons, but by itself, it might not be sufficient to avoid collisions in some situations (e.g., when used in hash-based collections or hashing algorithms).
    ^ (seed * 31) (XOR with a seed):

    XOR (^): The XOR operation is used to combine the original hash code with a modified value. This helps to distribute the hash values more evenly and reduce the likelihood of collisions. XOR is often used because it tends to scramble the bits in a way that makes the result more unpredictable and resistant to patterns.

    Multiplying the seed by 31: Multiplying the seed by 31 ensures that the seed is large enough to affect the final hash value. Using a constant like 31 is common because it’s a small prime number, and primes help in creating more evenly distributed hash values when used in hashing functions. It's often used in hashing algorithms (like in Java's String.hashCode() method itself).

    The Purpose:

    The combination of XOR with a seeded value and the original hashCode() helps mitigate potential weaknesses of using hashCode() alone, particularly in hash-based structures like hash maps, sets, or when used in more complex distributed systems like consistent hashing.
    The use of a seed allows for introducing randomness to the hash function, making it less predictable and reducing the risk of hash collisions in a broader context (e.g., for different elements or systems)
     */
    private int getHash(String element, int seed) {
        return Math.abs((element.hashCode() ^ (seed * 31)));
    }

    public static void main(String[] args) {
        // Create a Bloom Filter with 1000 bits and 3 hash functions
        PartBBloomFilter bloomFilter = new PartBBloomFilter(1000, 3);

        // Add elements to the Bloom Filter
        bloomFilter.add("hello");
        bloomFilter.add("world");

        // Check for elements in the Bloom Filter
        System.out.println("Might contain 'hello': " + bloomFilter.mightContain("hello")); // Likely true
        System.out.println("Might contain 'world': " + bloomFilter.mightContain("world")); // Likely true
        System.out.println("Might contain 'openai': " + bloomFilter.mightContain("openai")); // Likely false
    }
}