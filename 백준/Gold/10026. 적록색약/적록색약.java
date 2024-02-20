import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	
	static int N, cnt;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new char[N][N];
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		// 적록색약이 아닌 사람
		visit = new boolean[N][N];
		cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visit[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		System.out.print(cnt);
		
		// 적록색약을 위해 R -> G
		for (int i=0; i<N; i++)  {
			for (int j=0; j<N; j++) {
				if (map[i][j] == 'R') {
					map[i][j] = 'G';
				}
			}
		}
		
		// 적록색약인 사람
		visit = new boolean[N][N];
		cnt = 0;
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (!visit[i][j]) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		System.out.print(" " + cnt);
		
	}
	
	static void dfs(int x, int y) {
		char tmp = map[x][y];
		visit[x][y] = true;
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0>nx || nx>=N || 0>ny || ny>=N)	continue;
			if (map[nx][ny] == tmp && !visit[nx][ny]) {
				visit[nx][ny] = true;
				dfs(nx, ny);
			}
		}
	}
}
