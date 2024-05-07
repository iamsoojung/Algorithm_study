import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, M, answer;
	static int[][] graph;
	static boolean[] visit;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 마을에 사는 사람 수
			M = Integer.parseInt(st.nextToken());	// 서로 알고 있는 사람의 관계 ㅅ

			graph = new int[N+1][N+1];
			visit = new boolean[N+1];
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				graph[a][b] = 1;
				graph[b][a] = 1;
			}

			answer = 0;
			for (int i=1; i<=N; i++) {
				if (!visit[i]) {
					dfs(i);
					answer++;
				}
			}
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	// 연결되어있는지 확인
	static void dfs(int start) {
		visit[start] = true;
		for (int i=1; i<=N; i++) {
			if (graph[start][i] == 1 && !visit[i]) {	// 연결됨 && 방문X
				dfs(i);
			}
		}
		return;
	}
}
