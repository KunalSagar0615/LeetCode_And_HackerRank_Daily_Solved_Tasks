class Solution {
    public boolean isHappy(int n) {
        if(n<0)
            return false;

        HashSet<Integer> hs=new HashSet<>();

        while(!hs.contains(n)){
            hs.add(n);
            int sum=0;
            
            while(n>0){
                int rem=n%10;
                sum=sum+(rem*rem);
                n=n/10;
            }

            if(sum==1)
                return true;

            n=sum;
        }

        return false;
    }
}