import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int w, h, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0, -1, 1, -1, 1};
	static int[] dy = {0, 0, -1, 1, -1, -1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			answer = 0;
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());	// 너비
			h = Integer.parseInt(st.nextToken());	// 높이
			
			if (w==0 && h==0)	break;
			
			map = new int[h][w];
			visit = new boolean[h][w];
			for (int i=0; i<h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i=0; i<h; i++) {
				for (int j=0; j<w; j++) {
					if (map[i][j] == 1 && !visit[i][j]) {
						dfs(i, j);
						answer++;
					}
				}
			}	
			sb.append(answer).append("\n");
		}
		System.out.print(sb);
	}

	static void dfs(int x, int y) {
		
		visit[x][y] = true;
		
		for (int d=0; d<8; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];

			if (0<=nx && nx<h && 0<=ny && ny<w) {
				if (!visit[nx][ny] && map[nx][ny] == 1) {
					dfs(nx, ny);
				}
			}
		}
	}
}
