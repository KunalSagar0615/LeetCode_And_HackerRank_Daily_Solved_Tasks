class Solution {
    public void rotate(int[] nums, int k) {
        k=k%nums.length;

        int[] sub=Arrays.copyOfRange(nums,nums.length-k,nums.length);

        int index=nums.length-1;
        for(int i=nums.length-k-1; i>=0; i--){
            nums[index--]=nums[i];
        }

        for(int i=0; i<sub.length; i++){
            nums[i]=sub[i];
        }

    }
}