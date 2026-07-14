class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][][] dp;

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;

        dp = new long[n][201][201];

        for (int i = 0; i < n; i++) {
            for (int g1 = 0; g1 <= 200; g1++) {
                Arrays.fill(dp[i][g1], -1);
            }
        }

        return (int) solve(nums, 0, 0, 0);
    }

    private long solve(int[] nums, int idx, int g1, int g2) {
        if (idx == nums.length) {
            return (g1 == g2 && g1 != 0) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1) {
            return dp[idx][g1][g2];
        }

        long ans = 0;

        // Put nums[idx] in first subsequence
        ans += solve(nums, idx + 1, gcd(g1, nums[idx]), g2);

        // Put nums[idx] in second subsequence
        ans += solve(nums, idx + 1, g1, gcd(g2, nums[idx]));

        // Skip nums[idx]
        ans += solve(nums, idx + 1, g1, g2);

        ans %= MOD;

        return dp[idx][g1][g2] = ans;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}