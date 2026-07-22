class Solution {
    public boolean isNumber(String s) {
        if (s.equals("Infinity") || s.equals("-Infinity") || s.equals("+Infinity")|| s.equals("NaN"))
            return false;
        try{
            Double.parseDouble(s);

            char ch=s.charAt(s.length()-1);
            if(ch=='f' || ch=='F' || ch=='D' || ch=='d')
                return false;
            
            return true;
        }catch(Exception e){
            return false;
        }
    }
}