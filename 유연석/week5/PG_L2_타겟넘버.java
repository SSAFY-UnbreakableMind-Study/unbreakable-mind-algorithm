package week5;

class Solution {
    static int[] nums;
    static int T, ans;

    public int solution(int[] numbers, int target) {
        ans = 0;

        nums = numbers;
        T = target;
        dfs(0, 0);

        return ans;
    }

    public void dfs(int idx, int sum) {
        if (idx == nums.length) {
            if (sum == T) {
                ans++;
            }
            return;
        }
        dfs(idx + 1, sum + nums[idx]);
        dfs(idx + 1, sum - nums[idx]);
    }
}