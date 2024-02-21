import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	static int N, M, answer=0;
	static ArrayList<Integer>[] friends;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());	// 사람의 수
		M = Integer.parseInt(st.nextToken());	// 친구 관계의 수
		
		friends = new ArrayList[N];
		for (int i=0; i<N; i++) {
			friends[i] = new ArrayList<>();
		}
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			friends[a].add(b);
			friends[b].add(a);
		}
		
		visit = new boolean[N];
		for (int i=0; i<N; i++) {
			if (answer == 0) {
				dfs(1, i);
			}
		}
		
		System.out.print(answer);
	}
	
	static void dfs(int depth, int start) {
		if (depth == 5) {
			answer = 1;		// 존재하면 1
			return;
		}
		
		visit[start] = true;
		for(int i : friends[start]) {
			if (!visit[i]) {
				dfs(depth+1, i);
			}
		}
		visit[start] = false;
	}
}