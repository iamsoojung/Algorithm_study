class Solution {
    public String solution(String my_string) {
        String answer = "";
        
        char[] tmp = new char[my_string.length()];
        
        for (int i=0; i<my_string.length(); i++) {
            tmp[i] = my_string.charAt(i);
        }
        
        for (int i = tmp.length-1; i>=0; i--) {
            answer += tmp[i];
        }
        
        return answer;
    }
}