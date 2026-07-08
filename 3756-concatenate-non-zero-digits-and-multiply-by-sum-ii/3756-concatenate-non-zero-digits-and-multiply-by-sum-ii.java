// class Solution {
//     public int[] sumAndMultiply(String s, int[][] queries) {
//         String[] arr=s.split("");
//         int[] result=new int[queries.length];
//         int index=0;

//         for(int[] x:queries){
//             String[] subArr=Arrays.copyOfRange(arr,x[0],x[1]+1);

//             String sb="";
//             Long sum=0L;
//             for(String y:subArr){
//                 if(!y.equals("0")){
//                     sb=sb+y;
//                     sum+=Integer.parseInt(y);
//                 }
//             }

//             if(sb.isEmpty())
//                 result[index++]=0;
//             else 
//                 result[index++]=(int)(Long.parseLong(sb)*sum);
//         }


//         return result;
//     }
// }
class Solution {
    static final long MOD = 1_000_000_007L;

    public int[] sumAndMultiply(String s, int[][] queries) {
        int n = s.length();

        long[] pow10 = new long[n + 1];
        pow10[0] = 1;
        for (int i = 1; i <= n; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        long[] prefixSum = new long[n + 1];
        int[] prefixCount = new int[n + 1];
        long[] prefixValue = new long[n + 1];

        for (int i = 0; i < n; i++) {
            int digit = s.charAt(i) - '0';

            prefixSum[i + 1] = prefixSum[i];
            prefixCount[i + 1] = prefixCount[i];
            prefixValue[i + 1] = prefixValue[i];

            if (digit != 0) {
                prefixSum[i + 1] += digit;
                prefixCount[i + 1]++;
                prefixValue[i + 1] =
                        (prefixValue[i] * 10 + digit) % MOD;
            }
        }

        int[] ans = new int[queries.length];

        for (int k = 0; k < queries.length; k++) {
            int l = queries[k][0];
            int r = queries[k][1];

            long sum =
                    prefixSum[r + 1] - prefixSum[l];

            int digits =
                    prefixCount[r + 1] - prefixCount[l];

            if (digits == 0) {
                ans[k] = 0;
                continue;
            }

            long left =
                    (prefixValue[l] * pow10[digits]) % MOD;

            long num =
                    (prefixValue[r + 1] - left + MOD) % MOD;

            ans[k] = (int) ((num * (sum % MOD)) % MOD);
        }

        return ans;
    }
}