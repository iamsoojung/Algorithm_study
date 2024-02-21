import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int p[];
	static int rank[];
	static int answer;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());	// 마을에 사는 사람 수
			int M = Integer.parseInt(st.nextToken());	// 서로 알고 있는 사람의 관계 ㅅ
			
			p = new int[N+1];
			rank = new int[N+1];
			for (int i=1; i<=N; i++) {
				p[i] = i;
			}
			
			for (int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}

			answer = 0;
			for (int i=1; i<=N; i++) {
				if (p[i] == i) {
					answer++;
				}
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb);
	}

	static void union(int x, int y) {
		x = find(x);
		y = find(y);

		if (rank[x] < rank[y]) {
			p[x] = y;
			return;
		}
		if (rank[x] == rank[y])	rank[y]++;		
		p[y] = x;
	}
	
	static int find(int x) {
		if (p[x] == x) {
			return x;
		}
		return p[x] = find(p[x]);
	}
}
