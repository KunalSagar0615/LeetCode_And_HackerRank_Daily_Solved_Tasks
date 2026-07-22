class Solution {
    public int findKthLargest(int[] nums, int k) {
        return (int)Arrays.stream(nums).boxed().sorted(Comparator.reverseOrder()).skip(k-1).findFirst().get();
    }
}