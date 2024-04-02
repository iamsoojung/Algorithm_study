import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int M, N, K;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int testCase=1; testCase<=T; testCase++) {
			int cnt = 0;
			
			st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());	// 가로
			N = Integer.parseInt(st.nextToken());	// 세로
			K = Integer.parseInt(st.nextToken());	// 심어져 있는 위치 개수
			
			map = new int[N][M];
			
			int x, y;
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				
				map[y][x] = 1;
			}
			
			visit = new boolean[N][M];
			
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					// map 값이 1 && 방문 false
					if (map[i][j] == 1 && !visit[i][j]) {
						bfs(i, j);
						
						cnt++;
					}
				}
			}
			sb.append(cnt).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y}); 
		visit[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny) || visit[nx][ny])	continue;
				
				if (map[nx][ny] == 1) {
					q.add(new int[] {nx, ny});
					visit[nx][ny] = true;
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
