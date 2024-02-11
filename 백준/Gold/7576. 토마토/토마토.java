import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N;
	static int graph[][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<int[]> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 가로
		N = Integer.parseInt(st.nextToken());	// 세로
		graph = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 1) {
					q.add(new int[] {i, j});
				}
			}
		}
		
		dfs();
		
		int day = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (graph[i][j] == 0) {	// 익지 않았다면 주변도 안 익음
					System.out.println(-1);
					return;
				}
				day = Math.max(day, graph[i][j]);
			}
		}
		System.out.print(day-1);	// 며칠 지났는지
	}
	
	static void dfs() {
		while(!q.isEmpty()) {
			int[] n = q.poll();
			
			for (int i=0; i<4; i++) {
				int nx = n[0] + dx[i];
				int ny = n[1] + dy[i];
				
				// 범위 내에 있음 && 익지 않음
				if (0<=nx && nx < N && 0<=ny && ny<M && graph[nx][ny] == 0) {
					graph[nx][ny] = graph[n[0]][n[1]] + 1;
					q.add(new int[] {nx, ny});
				}
			}
		}
	}
}
