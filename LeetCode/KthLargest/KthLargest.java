/**
 * Find kth Largest Element in an array
 * Priority Queue is used to solve this problem
 * @author: Phani Thaticharla
 */

import java.util.PriorityQueue;

public class KthLargest {
    /**
     *
     * @param nums Input Array of Integers
     * @param k Find kth largest element from this array
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < nums.length; i++) {
            pq.add(nums[i]);
            if(pq.size() > k)
                pq.poll();
        }
        return pq.peek();
    }

    /**
     * Driver main
     * @param args
     */
    public static void main(String[] args) {
        KthLargest kthlargest = new KthLargest();
        int[] nums = new int[]{20, 30, 30, 80, 10, 50, 90, 90, 100, 60};
        int k = 5;
        int output = kthlargest.findKthLargest(nums, k);
        System.out.println("Kth Largest Element is " + output);
    }
}
