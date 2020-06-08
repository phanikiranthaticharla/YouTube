/**                                                                                                                                                                                                                                           
* Sliding Window Maximum                                                                                                                                                                                                                            
* Leetcode Problem #239                                                                                                                                                                                                                      
* @author: Phani Kiran Thaticharla                                                                                                                                                                                                            
*/


class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length == 0)
            return new int[0]; 
        
        int[] output = new int[nums.length - k + 1];
        ArrayDeque<Integer> ad = new ArrayDeque<>(); 
        
        for(int i = 0; i < nums.length; i++) {
            /* Whenever we move to a new window, discard previous window */
            if(!ad.isEmpty() && ad.peekFirst() == i - k) {
                ad.removeFirst(); 
            }    
            
            /* If element pointed by current index is greater than elements pointed by previous index,
            discard previous indexes */
            while(!ad.isEmpty() && nums[ad.peekLast()] < nums[i]) {
                ad.removeLast(); 
            }
            
            /* Add the new index to the end of the ArrayDeque */
            ad.offer(i); 
            
            
            /* When we find new window, we ll add the maximum in the window to the output */     
            if(i + 1 >= k) {
                output[i + 1 - k] = nums[ad.peekFirst()];  
            }
        }
        return output; 
    }
}
