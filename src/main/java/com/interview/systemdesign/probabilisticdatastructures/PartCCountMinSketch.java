package com.interview.systemdesign.probabilisticdatastructures;

/*
    Category: Hard, VVImp
    A Count-Min Sketch (CMS) is a probabilistic data structure that provides approximate answers to point queries on frequency distributions.
     It is useful for estimating the frequency of events in a data stream, while using much less space than an exact count would require.
     Count-Min Sketch allows both approximate frequency counts and range queries with some error, trading off space and accuracy.

    Key Features of Count-Min Sketch:
    - Space Efficient: Uses less memory than storing exact counts for all elements.
    - Fast Updates and Queries: Allows for quick insertion and retrieval operations.
    - Approximate Answers: The counts are approximate, with an upper bound on the error.

    How Count-Min Sketch Works:
    1. Count-Min Sketch is made up of a 2D array of counters (d rows and w columns).
    2. Each element in the data stream is hashed using d different hash functions, each returning an index between 0 and w-1.
    3. Each time an element appears in the stream, we increment the corresponding counter for each hash function.
    4. To estimate the frequency of an element, we return the minimum value among the d counters that correspond to the element’s hash values.

    The probability of overestimating the frequency decreases as the number of hash functions (d) increases. However, with more rows and columns,
     the space requirement also increases.

    Count-Min Sketch Characteristics:
    - Supports Approximate Frequency Queries: Returns the approximate count of an element in the stream.
    - Compact: Uses much less space than traditional hash maps or other counting data structures.
    - No False Negatives: The count will never be less than the true count, but it might be more (due to hash collisions).
    - Fast: Insertions and lookups are performed in constant time (O(d)).

    Applications of Count-Min Sketch:
    - Network Traffic Monitoring: To count the frequency of packets with certain headers.
    - Databases: To count distinct values in large databases for query optimization.
    - Streaming Analytics: Used in systems like Apache Spark and Flink to track the popularity of items in real-time.
    - Web Search Engines: To track the frequency of search terms.
    - Fraud Detection: To monitor unusual access patterns and behaviors in large-scale systems.
    - Website url traffic

    Explanation of the Code:
    - sketch: A 2D array of integers where the counts are stored.
    - hashFunctions: An array of hash functions used to determine the row index in the sketch for each element.
    - add(T element): Adds an element to the sketch by incrementing the corresponding counters using the hash functions.
    - estimateCount(T element): Returns the estimated count of the element by taking the minimum value across all hash function outputs.

    Advantages of Using Count-Min Sketch:
    - Compact: Requires much less memory than storing exact counts for all elements.
    - No False Negatives: Always provides an upper bound on the count (but might overestimate).

    Disadvantages:
    - Overestimation: The count might be higher than the true value due to hash collisions.
    - Approximate Counts: Cannot return the exact count of an element.

    Parameters for Optimizing Count-Min Sketch:
    - Number of Hash Functions (d): More hash functions reduce the probability of overestimation but increase the computation cost.
    - Width of the Sketch (w): A larger width reduces collisions but requires more memory.

    The ideal values for d and w depend on the desired accuracy and space constraints.

    Real-World Example:
    In systems like Twitter or Reddit, Count-Min Sketch can be used to count the number of times a hashtag or keyword appears in a real-time data stream. By using CMS, these systems can track trends without needing to store every keyword in memory, thereby saving a huge amount of space.
*/

public class PartCCountMinSketch {

    private final int[][] sketch; // 2D array to store counts
    private final int columns; // Number of columns (width)
    private final int rows; // Number of rows (depth)

    /**
     * Constructor to initialize the Count-Min Sketch with specified columns and rows.
     *
     * @param columns The number of columns in the sketch.
     * @param rows The number of rows in the sketch.
     */
    public PartCCountMinSketch(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.sketch = new int[rows][columns]; // Initialize the 2D array
    }

    /**
     * Adds an element to the Count-Min Sketch.
     *
     * @param element The element to be added.
     */
    public void add(String element) {
        for (int i = 0; i < rows; i++) {
            int index = getHash(element, i) % columns; // Compute column index
            sketch[i][index]++; // Increment the counter
        }
    }

    /**
     * Estimates the count of an element in the sketch.
     *
     * @param element The element to estimate.
     * @return The estimated count of the element.
     */
    public int estimateCount(String element) {
        int minCount = Integer.MAX_VALUE;
        for (int i = 0; i < rows; i++) {
            int index = getHash(element, i) % columns; // Compute column index
            minCount = Math.min(minCount, sketch[i][index]); // Take the minimum value
        }
        return minCount; // Return the estimated count
    }

    /**
     * A simple hash function that combines the element and the row index.
     *
     * @param element The element to hash.
     * @param seed    The seed to vary the hash.
     * @return A hash value for the element.
     */
    private int getHash(String element, int seed) {
        return Math.abs((element.hashCode() ^ (seed * 31)));
    }

    public static void main(String[] args) {
        // Create a Count-Min Sketch with 100 columns and 3 rows
        PartCCountMinSketch cms = new PartCCountMinSketch(100, 3);

        // Add elements to the Count-Min Sketch
        cms.add("apple");
        cms.add("banana");
        cms.add("apple"); // Add "apple" again

        // Estimate counts of elements
        System.out.println("Estimated count of apple: " + cms.estimateCount("apple")); // Likely 2
        System.out.println("Estimated count of banana: " + cms.estimateCount("banana")); // Likely 1
        System.out.println("Estimated count of cherry: " + cms.estimateCount("cherry")); // Likely 0
    }
}

