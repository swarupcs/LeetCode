import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        //Array to store result
        int[] result = new int[2];
        //This map will store the difference and the corresponding index
        Map<Integer, Integer> map = new HashMap<>();
        //Traverse the entire array
        for(int i =0; i<nums.length; i++) {
            //if we seen the current element before then we have already encountered the other number of pair
            if(map.containsKey(nums[i])) {
                //index of the current element
                result[0] = i;
                //index of the other element of the pair
                result[1] = map.get(nums[i]);
            }
            //if we have not seen the current before, it means we have not yet encountered any number of the pair
             else {
                // Save the difference of the target and the current element
                // with the index of the current element
                map.put(target - nums[i], i);
            }
           
        }
         return result;
    }
}