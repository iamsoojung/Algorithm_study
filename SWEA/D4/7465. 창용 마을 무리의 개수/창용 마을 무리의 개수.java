import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, answer;
	static ArrayList<Integer>[] arr;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());	// 사람 수
			M = Integer.parseInt(st.nextToken());	// 사람 간 관계 수
			
			arr = new ArrayList[N+1];
			for (int i=1; i<=N; i++) {
				arr[i] = new ArrayList<>();
			}
			
			int a, b;
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				arr[a].add(b);
				arr[b].add(a);
			}
			
//			for (int i=1; i<=N; i++) {
//				System.out.println(arr[i].toString());
//			}
			
			visit = new boolean[N+1];
			answer = 0;
			for (int i=1; i<=N; i++) {
				if (!visit[i]) {
					answer++;
					bfs(i);
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void bfs(int idx) {
		Queue<Integer> q = new ArrayDeque<>();
		q.add(idx);
		visit[idx] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			visit[cur] = true;
			
			for (int i=0; i<arr[cur].size(); i++) {
				int next = arr[cur].get(i);
				if (!visit[next]) {					
					q.add(next);
				}
			}
		}
	}
}
