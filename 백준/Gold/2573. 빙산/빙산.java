import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer = 0;
	static int[][] map;
	static Queue<int[]> q = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > 0) {
					q.add(new int[] {i, j});
				}
			}
		}
			
		answer = bfs();

		System.out.println(answer);
	}

	static int bfs() {
		int time = 0;
		while(!q.isEmpty()) {
			
			if (check(q.peek())) {
				return time;
			}
			
			// 맵 복사
			int[][] tmp = new int[N][M];
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					tmp[i][j] = map[i][j];
				}
			}
			
			int size = q.size();
			for (int i=0; i<size; i++) {
				int[] cur = q.poll();
				int cx = cur[0];
				int cy = cur[1];
				
				int cnt = 0;
				for (int d=0; d<4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if (!inRange(nx, ny))	continue;
					if (map[nx][ny] == 0) {
						cnt++;
					}
				}
				
				if (map[cx][cy] <= cnt) {	// 다 녹음
					tmp[cx][cy] = 0;
				} else {
					tmp[cx][cy] -= cnt;
					q.add(new int[] {cx, cy});
				}
			}
			time++;

			// 맵 복사
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					map[i][j] = tmp[i][j];
				}
			}
		}
		return 0;
	}
	
	static boolean check(int[] v) {
		boolean[][] visit = new boolean[N][M];
		
		Queue<int[]> qq = new ArrayDeque<>();
		qq.add(new int[] {v[0], v[1]});
		visit[v[0]][v[1]] = true;
		
		int cnt = 0;
		while(!qq.isEmpty()) {
			int[] cur = qq.poll();
			int cx = cur[0];
			int cy = cur[1];
			cnt++;
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 0)	continue;
				
				qq.add(new int[] {nx, ny});
				visit[nx][ny] = true;
			}
		}
		
		if (cnt == q.size()) {	// 한 덩어리
			return false;
		} else {
			return true;
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
