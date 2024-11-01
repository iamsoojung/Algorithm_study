// 논문 n편중, h번이상인용된 논문이 h편이상이고 나머지가 h번이하인용됐다면 -> h최대값 == H-Index
import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        for (int i=0; i<citations.length; i++) {
            int h = citations.length - i;   // 남은 논문 수
            
            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}