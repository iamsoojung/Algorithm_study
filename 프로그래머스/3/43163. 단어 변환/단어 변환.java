import java.io.*;
import java.util.*;

class Solution {
    
    public static boolean[] isVisited;
    public static int answer = 0;
    
    public int solution(String begin, String target, String[] words) {
        
        isVisited = new boolean[words.length];
        findTarget(begin, target, words, 0);
                
        return answer;
    }
    
    public static void findTarget(String begin, String target, String[] words, int depth) {
        if (begin.equals(target)) {
            answer = depth;
            return;
        }
        
        for (int i=0; i<words.length; i++) {
            
            if (!isVisited[i]) {
                int cnt = 0;
                
                for (int j=0; j<words[i].length(); j++) {
                    if (begin.charAt(j) == words[i].charAt(j)) {
                        cnt++;
                    }
                }
                
                if (cnt == begin.length()-1) {
                    isVisited[i] = true;
                    findTarget(words[i], target, words, depth+1);
                    isVisited[i] = false;
                }
            }
        }
    }
}