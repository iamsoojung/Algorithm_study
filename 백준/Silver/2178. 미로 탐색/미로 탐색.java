import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] visit;
	static Queue<int[]> q = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		visit = new int[N][M];
		
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		bfs(0, 0);
		
		System.out.println(visit[N-1][M-1]);		
	}
	
	static void bfs(int x, int y) {
		visit[x][y] = 1;
		q.add(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for(int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (0 > nx || nx >= N || 0 > ny || ny >= M)	continue;
				
				if(visit[nx][ny] == 0 && map[nx][ny] == 1) {
					visit[nx][ny] = visit[cur[0]][cur[1]]+1;
					q.add(new int[] {nx, ny});
				}
			}			
		}
	}
}
