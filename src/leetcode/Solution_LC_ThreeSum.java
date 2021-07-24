package leetcode;

import java.util.*;

class Solution_LC_ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();

        Arrays.sort(nums);

        int prev = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num <= prev)
                continue;

            int target = num * (-1);
            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                if (nums[left] + nums[right] > target)
                    right--;
                else if (nums[left] + nums[right] < target)
                    left++;
                else {
                    ArrayList<Integer> candidate = new ArrayList<>();
                    candidate.add(nums[i]);
                    candidate.add(nums[left]);
                    candidate.add(nums[right]);

                    answer.add(candidate);
                    while (left < nums.length - 1 && nums[left] == nums[++left]) ;
                    while (right > 0 && nums[right] == nums[--right]) ;
                }
            }

            prev = nums[i];
        }

        return answer;
    }

    public static void main(String[] args) {
        Solution_LC_ThreeSum sol = new Solution_LC_ThreeSum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(sol.threeSum(nums));
    }
}
