/**
 * Calculate Top K Elements from a given array by two different ways:
 * Sorting Method
 * Priority Queue Method
 * @author: Phani Kiran Thaticharla
 */

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TopKFrequentElements {

    static Map<Integer, Integer> map; // Map that stores each number and it's number of occurrences

    /**
     * Comparator used by Priority Queue to insert numbers
     * with least frequency(MinHeap) at the Top
     */
    static class PriorityQueueComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return map.get(a) - map.get(b);
        }
    }

    /**
     * Comparator for sorting given numbers in descending order based on Frequency
     */
    static class SortingComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            return map.get(b) - map.get(a);
        }
    }

    /**
     * Function that creates HashMap of each number and it's frequency
     * @param nums
     */
    private void createHashMap(int[] nums) {
        map = new HashMap();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        System.out.println("Created HashMap. Total number of keys in HashMap: " + map.keySet().size());
    }

    /**
     * Function to print the output array with K elements and it's frequency
     * @param output
     */
    private void printOutput(int[] output) {

        for(int i = 0; i < output.length; i++) {
            System.out.print(output[i] + ":" + map.get(output[i]) + " ");
        }
        System.out.println();
    }

    /**
     * Function to read the random numbers from a file to nums array
     * @param nums
     * @param filename
     * @return
     * @throws IOException
     */
    private int[] readFromFile(int[] nums, String filename) throws IOException {
        Path filePath = Paths.get("/home/phani/projects/TopKFrequentElements/src/" + filename);
        Scanner scanner = new Scanner(filePath);
        int i = 0;
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                nums[i++] = scanner.nextInt();
            } else {
                scanner.next();
            }
        }
        scanner.close();
        return nums;
    }

    /**
     * Function that returns Top K elements by Sorting the input array
     * @param k
     * @return
     */
    private int[] findTopKBySorting(int k) {
        ArrayList<Integer> keys = new ArrayList<>();
        keys.addAll(map.keySet());
        Collections.sort(keys, new SortingComparator());
        int[] output = new int[k];
        for(int i = k - 1; i >=0; i--) {
            output[i] = keys.get(i);
        }
        return output;
    }

    /**
     * Function to calculate Top K Elements by using Min Heap
     * @param k
     * @return
     */
    private int[] findTopKByPriorityQueue(int k) {
        PriorityQueue<Integer> pq = new PriorityQueue(new PriorityQueueComparator());
        for(int num : map.keySet()) {
            // If pq contains k elements and next number's frequency in HashMap is lower than
            // the top element in the Priority Queue, no need to add this number
            if(pq.size() == k && num < pq.peek())
               continue;
            pq.add(num);
            // Add the number to Priority Queue and if the size of pq > k, remove the least frequent element
            if(pq.size() > k)
                pq.poll();
        }
        int[] output = new int[k];
        for(int i = k - 1; i >=0; i--) {
            output[i] = pq.poll();
        }
        return output;
    }

    /**
     * Driver Main
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        TopKFrequentElements topKFreq = new TopKFrequentElements();
        int INPUT_SIZE = 10000000; // 10 Million Random Numbers
        int K = 10; // Find the Top 10 numbers based on the frequency
        int[] nums = new int[INPUT_SIZE];
        // String filename = "input1.txt"; // contains 400,000 unique numbers out of 10 Million Numbers
        String filename = "input2.txt"; // each number is unique out of the 10 Million Numbers
        nums = topKFreq.readFromFile(nums, filename);
        topKFreq.createHashMap(nums);

        long startTime = System.currentTimeMillis();
        int[] output = topKFreq.findTopKBySorting(K);
        long stopTime = System.currentTimeMillis();
        long calculationTime = stopTime - startTime;
        System.out.println("Top K values found using Sorting Method: ");
        topKFreq.printOutput(output);
        System.out.println("Sorting Method Calculation time in milliseconds " + calculationTime);
        System.out.println();

        startTime = System.currentTimeMillis();
        output = topKFreq.findTopKByPriorityQueue(K);
        stopTime = System.currentTimeMillis();
        calculationTime = stopTime - startTime;
        System.out.println("Top K values found using Priority Queue: ");
        topKFreq.printOutput(output);
        System.out.println("MinHeap calculation time in milliseconds " + calculationTime + "\n");

    }
}
