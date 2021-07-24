package leetcode;

import java.util.*;

class Solution_LC_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] answer = new int[2];

        // for (int i=0; i<nums.length; i++) {
        //     for (int j=i+1; j<nums.length; j++) {
        //         if (nums[i]+nums[j] == target) {
        //             answer[0] = i;
        //             answer[1] = j;
        //             return answer;
        //         }
        //     }
        // }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];

            if (map.containsKey(nums[i])) {
                int idx = map.get(nums[i]);
                answer[0] = i;
                answer[1] = idx;
                return answer;

            } else {
                map.put(diff, i);
            }
        }


        return answer;
    }
}
