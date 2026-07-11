class Solution {
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> lst=new ArrayList<>();

        for(int i=left; i<=right; i++){
            
            int temp=i;
            boolean flag=true;
            
            while(temp>0){

                int r=temp%10;
                if(r==0 || i%r!=0){
                    flag=false;
                    break;
                }

                temp/=10;
            }

            if(flag)
                lst.add(i);
        }

        return lst;
    }
}