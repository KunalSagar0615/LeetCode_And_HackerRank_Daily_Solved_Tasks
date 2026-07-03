// class Solution {
//     public static boolean isPrime(int no){
//         if(no==2 || no==3)
//             return true;

//         int cnt=0;
//         for(int i=2; i<=no/2; i++){
//             if(no%i==0){
//                 return false;
//             }
//         }

//         return true;
//     }

//     public int countPrimes(int n) {
//         int cnt=0;
//         for(int i=2; i<n; i++){
//             if(i%2!=0 || i==2){
//                 if(isPrime(i))
//                     cnt++;
//             }
//         }

//         return cnt;
//     }
// }
class Solution {
    public int countPrimes(int n) {
        if (n <= 2) return 0;

        boolean[] prime = new boolean[n];

        for (int i = 2; i < n; i++) {
            prime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (prime[i]) {
                for (int j = i * i; j < n; j += i) {
                    prime[j] = false;
                }
            }
        }

        int count = 0;

        for (int i = 2; i < n; i++) {
            if (prime[i]) count++;
        }

        return count;
    }
}