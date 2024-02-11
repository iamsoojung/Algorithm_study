import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, V, arr[][];
	static boolean[] visited;
	static Queue<Integer> q = new LinkedList<>();
	static StringBuilder sb;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		arr = new int[N+1][N+1];
		
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 연결되어있다는 뜻
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		// DFS
		visited = new boolean[N+1];
		dfs(V);
		
		sb.append("\n");
		
		// BFS
		visited = new boolean[N+1];
		bfs(V);
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
	public static void dfs(int start) {
		visited[start] = true;
		sb.append(start).append(" ");
		
		for (int i=0; i<=N; i++) {
			if (arr[start][i] == 1 && visited[i] == false) {
				dfs(i);
			}
		}
	}
	
	public static void bfs (int start) {
		visited[start] = true;
		q.add(start);
		
		while (!q.isEmpty()) {
			start = q.poll();
			sb.append(start).append(" ");
			for (int i=1; i<=N; i++) {
				if (arr[start][i] == 1 && visited[i] == false) {
					visited[i] = true;
					q.add(i);
				}
			}
		}
			
		
		
	}
}
