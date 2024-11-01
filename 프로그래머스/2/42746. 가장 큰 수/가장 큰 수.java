import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        String[] str = new String[numbers.length];
        for (int i=0; i<numbers.length; i++) {
            str[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(str, (o2, o1) -> (o1+o2).compareTo(o2+o1));
        
        // 첫숫자가 0인경우 그냥 0임
        if (str[0].equals("0")) {
            return "0";
        }
        
        for (int i=0; i<str.length; i++) {
            answer += str[i];
        }
        
        return answer;
    }
}