class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;

        if (n < 3)
            return n;

        int x = (int) (Math.log(n) / Math.log(2));

        return 1 << (x + 1);
    }
}