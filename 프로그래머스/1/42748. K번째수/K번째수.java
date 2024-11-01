// i번째 숫자부터 j번째 숫자까지 자르고 정렬할 때, k번째에 있는 수
// (i-1 ~ j) 에서 k-1번째
import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int c=0; c<commands.length; c++) {
            ArrayList<Integer> newArray = new ArrayList<>();

            for (int idx=commands[c][0]-1; idx<=commands[c][1]-1; idx++) {
                newArray.add(array[idx]);
            }
            
            Collections.sort(newArray);
            answer[c] = newArray.get(commands[c][2]-1);
        }
        
        return answer;
    }
}