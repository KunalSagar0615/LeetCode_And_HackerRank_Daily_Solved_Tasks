// class Solution {
//     public int uniqueXorTriplets(int[] nums) {

//         Set<Integer> pair = new HashSet<>();
//         Set<Integer> ans = new HashSet<>();

//         // Find all possible XOR of 2 numbers
//         for (int a : nums) {
//             for (int b : nums) {
//                 pair.add(a ^ b);
//             }
//         }

//         // Add third number
//         for (int x : pair) {
//             for (int num : nums) {
//                 ans.add(x ^ num);
//             }
//         }

//         return ans.size();
//     }
// }

class Solution {
    public int uniqueXorTriplets(int[] nums) {

        int max = 0;

        for (int num : nums) {
            max = Math.max(max, num);
        }

        int size = 1;
        while (size <= max) {
            size *= 2;
        }

        boolean[] present = new boolean[size];

        for (int num : nums) {
            present[num] = true;
        }

        boolean[] pair = new boolean[size];

        for (int a = 0; a < size; a++) {
            if (!present[a]) continue;

            for (int b = 0; b < size; b++) {
                if (present[b]) {
                    pair[a ^ b] = true;
                }
            }
        }

        boolean[] ans = new boolean[size];

        for (int x = 0; x < size; x++) {
            if (!pair[x]) continue;

            for (int num : nums) {
                ans[x ^ num] = true;
            }
        }

        int count = 0;

        for (boolean value : ans) {
            if (value)
                count++;
        }

        return count;
    }
}