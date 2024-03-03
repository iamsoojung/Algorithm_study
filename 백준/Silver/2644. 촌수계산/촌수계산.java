import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int n, a, b, m, answer=-1;
	static ArrayList<Integer>[] arr;
	static boolean[] visit;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());	// 전체 사람의 수
		visit = new boolean[n+1];
		arr = new ArrayList[n+1];
		for (int i=0; i<n+1; i++) {
			arr[i] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		a = Integer.parseInt(st.nextToken());	// 촌수 계산할 두 사람의 번호
		b = Integer.parseInt(st.nextToken());
		
		m = Integer.parseInt(br.readLine());	// 관계의 개수
		
		int x, y;
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			arr[x].add(y);	// 양방향 트리
			arr[y].add(x);
		}
		
		dfs(a, b, 0);
		System.out.print(answer);
	}
	
	static void dfs(int start, int end, int cnt) {
		if (start == end) {
			answer = cnt;
			return;
		}
		visit[start] = true;
		for (int i : arr[start]) {
			if (!visit[i]) {
				visit[i] = true;
				dfs(i, end, cnt+1);
			}
		}
		return;
	}
}
