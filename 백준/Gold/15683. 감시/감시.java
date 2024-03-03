import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer = Integer.MAX_VALUE;
	static int[][] map;
	static ArrayList<cInfo> cctv = new ArrayList<>();
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static int[][] dir = {{}, {0}, {0,2}, {0,3}, {0,2,3}, {0,1,2,3}};	// 1~5 방향
	
	static class cInfo {
		int x, y, num;

		public cInfo(int x, int y, int num) {
			super();
			this.x = x;
			this.y = y;
			this.num = num;
		}	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (0 < map[i][j] && map[i][j] < 6) {
					cctv.add(new cInfo(i, j, map[i][j]));
				}
			}
		}
		
		dfs(0, map);
		
		System.out.println(answer);
	}
	
	static void dfs(int idx, int[][] curMap) {
		if (idx == cctv.size()) {	// 모든 cctv 다 봤다!
			// 사각지대 계산
			int cnt = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					if (curMap[i][j] == 0)	cnt++;
				}
			}
			answer = Math.min(answer, cnt);
			return;
		}
		
		int cx = cctv.get(idx).x;
		int cy = cctv.get(idx).y;
		int cnum = cctv.get(idx).num;
		
		for (int d=0; d<4; d++) {	// ~~ 사방위로 탐색
			int[][] cmap = new int[N][M];	// 맵 복사 (2차원 배열은 반복문으로 복사, clone() 불가능..)
			for (int i=0; i<N; i++) {
				for (int j=0; j<M; j++) {
					cmap[i][j] = curMap[i][j];
				}
			}
			
			for (int n : dir[cnum]) {	// 가능한 방향을 ~~
				int ndir = (n+d)%4;
				int nx = cx;
				int ny = cy;
				
				while(true) {	// 벽 만나기 전까지 쭉 가야함
					nx += dx[ndir];
					ny += dy[ndir];
					
					// 범위 벗어난 경우 or 벽 만난 경우 => 반복 종료
					if (0>nx || nx>=N || 0>ny || ny>=M || cmap[nx][ny]==6)	break;
					
					cmap[nx][ny] = -1;	// 감시 ON
				}
			}
			dfs(idx+1, cmap);
		}
		
	}
	
}
