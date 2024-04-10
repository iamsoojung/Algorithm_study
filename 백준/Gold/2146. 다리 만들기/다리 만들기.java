import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j]= Integer.parseInt(st.nextToken());
			}
		}
		
		// 1. 섬들 그룹핑
		int areaNum = 2;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 1) {
					dfs(i, j, areaNum++);
				}
			}
		}
		
		// 2. 하나의 섬에서 다른 섬까지의 최단거리구하기
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (map[i][j] >= 2) {
					visit = new boolean[N][N];
					bfs(i, j, map[i][j]);
				}
			}
		}
		
		System.out.println(answer);
	}
	
	static void bfs(int x, int y, int num) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {x, y, 0});
		visit[x][y] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cCnt = cur[2];
			
			// 최솟값보다 크면 굳이 볼 필요 없음
			if (cCnt > answer)	return;
			
			// 최솟값 갱신
			if (map[cx][cy] != 0 && map[cx][cy] != num) {
				answer = Math.min(answer, cCnt-1);
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])		continue;
				
				if (map[nx][ny] != num)	{	// 같은 섬이 아닌 연결된 바다
					q.add(new int[] {nx, ny, cCnt+1});
					visit[nx][ny] = true;
				}
			}
		}
	}
	
	static void dfs(int x, int y, int num) { 
		map[x][y] = num;
		visit[x][y] = true;
		
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (!inRange(nx, ny))	continue;
			if (visit[nx][ny])	continue;
			
			if (map[nx][ny] == 1) {
				dfs(nx, ny, num);
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
