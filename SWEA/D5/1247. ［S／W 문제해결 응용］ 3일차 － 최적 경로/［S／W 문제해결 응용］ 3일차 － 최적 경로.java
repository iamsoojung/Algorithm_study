import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static StringBuilder sb = new StringBuilder();
	static int N, min;
	static int[][] location;	// 주어진 위치
	static boolean[] visit;		// 방문 여부
	static int[] result;	// 방문 순서 저장할 배열
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());

			location = new int[N+2][2];	// 회사 , N명의 고객, 집
			
			location[0][0] = Integer.parseInt(st.nextToken());
			location[0][1] = Integer.parseInt(st.nextToken());
			location[N+1][0] = Integer.parseInt(st.nextToken());
			location[N+1][1] = Integer.parseInt(st.nextToken());
			for (int i=1; i<=N; i++) {
				location[i][0] = Integer.parseInt(st.nextToken());
				location[i][1] = Integer.parseInt(st.nextToken());
			}
			
			min = Integer.MAX_VALUE;
			visit = new boolean[N+1];
			result = new int[N];
			dfs(0);
			sb.append("#" + test_case + " " +min + "\n");
		}
		System.out.println(sb);
	}
	
	static void dfs(int cnt) {
		if (cnt == N) {
			int sum = 0;
			int r = location[0][0];
			int c = location[0][1];
			
			for (int i=0; i<N; i++) {
				sum += Math.abs(r-location[result[i]][0]) + Math.abs(c-location[result[i]][1]);
				r = location[result[i]][0];
				c = location[result[i]][1];
			}
			sum += (Math.abs(location[N+1][0]-r) + Math.abs(location[N+1][1]-c));
			min = Math.min(min, sum);
			
		}
		
		for (int i=1; i<=N; i++) {
			if (!visit[i]) {
				result[cnt] = i;
				visit[i] = true;
				dfs(cnt+1);
				visit[i] = false;				
			}
		}
	}
}
