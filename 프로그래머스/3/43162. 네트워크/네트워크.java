import java.util.*;
import java.io.*;

class Solution {
    
    static boolean[] isVisited;
    
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        isVisited = new boolean[n];
        for (int i=0; i<n; i++) {
            if (!isVisited[i]) {
                dfs(i, computers);
                answer++;
            }
        }
        
        return answer;
    }
    
    static void dfs(int idx, int[][] computers) {
        isVisited[idx] = true;    
        
        for (int i=0; i<computers.length; i++) {
            if (computers[idx][i] == 1 && !isVisited[i]) {    // 연결되어있고, 아직 방문하지 않았다면
                dfs(i, computers);
            }
        }
    }
}
