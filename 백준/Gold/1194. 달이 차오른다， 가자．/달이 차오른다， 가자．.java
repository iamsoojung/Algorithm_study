import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static char[][] map;
	static boolean[][][] visit;		// 열쇠 방문 여부
	static boolean haveKey;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		
		int startX = 0, startY = 0;
		for (int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
			for (int j=0; j<M; j++) {	// 민식's 위치
				if (map[i][j] == '0') {
					startX = i;		startY = j;
				}
			}
		}
		
		visit = new boolean[N][M][1<<6];
		
		sb.append(bfs(startX, startY));
		System.out.print(sb.toString());
	}
	
	static int bfs(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		int startIdx = 0;	// visit 인덱스 0 (열쇠 없음)
		q.offer(new int[] {startX, startY, startIdx, 0});	// visit 인덱스, 이동 횟수
		visit[startX][startY][startIdx] = true;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			int cIdx = cur[2];
			int cDist = cur[3];
			
			if (map[cx][cy] == '1') {	// 탈출 (종료조건)
				return cDist;
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;	// 범위 체크
				
				int n = map[nx][ny];
				if (n == '#')	continue;	// 벽
				
				// 열쇠
				if ('a' <= n && n <= 'f') {
					int nIdx = (cIdx) | (1 << (n-'a'));		// 열쇠 가짐
					if (!visit[nx][ny][nIdx]) {
						q.offer(new int[] {nx, ny, nIdx, cDist+1});
						visit[nx][ny][nIdx] = true;
					}
				}
				
				// 문
				else if ('A' <= n && n <= 'F') {
					int check = (cIdx) & (1 << (n-'A'));
					if (check > 0) {	// 그 열쇠가 있다면
						if (!visit[nx][ny][cIdx]) {
							q.offer(new int[] {nx, ny, cIdx, cDist+1});
							visit[nx][ny][cIdx] = true;
						}
					}
				}
				
				else {
					if (!visit[nx][ny][cIdx]) {
						q.offer(new int[] {nx, ny, cIdx, cDist+1});
						visit[nx][ny][cIdx] = true;
					}
				}
			}
		}
		return -1;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
