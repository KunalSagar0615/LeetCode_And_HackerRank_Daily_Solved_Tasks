class Solution {
    public int numOfStrings(String[] patterns, String word) {
        
        int cnt=0;

        for(String x: patterns){
            if(word.contains(x))
                cnt++;
        }

        return cnt;
    }
}