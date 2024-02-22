import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

    static int size, start;
    static int max, answer;
    static ArrayList<Integer>[] networks;
    static int[] visit;
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        for (int testCase=1; testCase<=10; testCase++) {
            st = new StringTokenizer(br.readLine());
            size = Integer.parseInt(st.nextToken());    // 입력 받는 데이터 길이
            start = Integer.parseInt(st.nextToken());    // 시작점
            
            networks = new ArrayList[101];
            visit = new int[101];
            
            for (int i=0; i<101; i++) {
                networks[i] = new ArrayList<>();
            }
            
            st = new StringTokenizer(br.readLine());
            for (int i=0; i<size/2; i++) {
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                networks[from].add(to);
            }
            
            max = 0;
            answer = 0;
            bfs(start);
            
            for (int i=0; i<visit.length; i++) {
            	if (visit[i] >= max) {
            		max = visit[i];
            		if (i > answer) {
            			answer = i;
            		}
            	}
            }
            sb.append("#").append(testCase).append(" ").append(answer).append("\n");
        }
        System.out.print(sb.toString());
    }
    
    static void bfs(int x) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(x);
        visit[x] = 1;
        
        while(!q.isEmpty()) {
            int pick = q.poll();
            
            for (int p : networks[pick]) {
                if (visit[p] == 0) {
                    q.offer(p);
                    visit[p] = visit[pick] + 1;	// 현재 = 과거+1
                }
            }
        }
    }
    
}