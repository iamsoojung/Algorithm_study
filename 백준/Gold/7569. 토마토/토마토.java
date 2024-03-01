import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int M, N, H;
	static int graph[][][];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static Queue<int[]> q = new ArrayDeque<>();
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 가로
		N = Integer.parseInt(st.nextToken());	// 세로
		H = Integer.parseInt(st.nextToken());	// 높이
		graph = new int[N][M][H];
		
		for (int h=0; h<H; h++) {
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<M; j++) {
					graph[i][j][h] = Integer.parseInt(st.nextToken());
					if (graph[i][j][h] == 1) {
						q.add(new int[] {i, j, h});
					}
				}
			}
		}
		
		bfs();
		
		int day = 0;
		for (int h=0; h<H; h++) {
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (graph[i][j][h] == 0) {	// 익지 않았다면 주변도 안 익음
						System.out.println(-1);
						return;
					}
					day = Math.max(day, graph[i][j][h]);
				}
			}
		}
		System.out.print(day-1);	// 며칠 지났는지
	}
	
	static void bfs() {
		
		while(!q.isEmpty()) {
			int[] n = q.poll();
			int cx = n[0];
			int cy = n[1];
			int ch = n[2];
			
			// 같은 층에서
			for (int i=0; i<4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				
				// 범위 내에 있음 && 익지 않음
				if (0<=nx && nx < N && 0<=ny && ny<M && graph[nx][ny][ch] == 0) {
					graph[nx][ny][ch] = graph[cx][cy][ch] + 1;
					q.add(new int[] {nx, ny, ch});
				}
			}
			
			// 다른 층끼리
			if (0 <= ch-1 && graph[cx][cy][ch-1] == 0) {
				graph[cx][cy][ch-1] = graph[cx][cy][ch] + 1;
				q.add(new int[] {cx, cy, ch-1});
			}
			if (ch+1 < H && graph[cx][cy][ch+1] == 0) {
				graph[cx][cy][ch+1] = graph[cx][cy][ch] + 1;
				q.add(new int[] {cx, cy, ch+1});
			}
		}
	}
}