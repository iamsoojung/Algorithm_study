import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, answer = 0;
	static int[][] map;
	static int[] dx = {0, 1, 1};
	static int[] dy = {1, 1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());	// 집의 크기 N
		map = new int[N][N];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}		
		dfs(0, 1 ,0);
		System.out.print(answer);
	}
	static void dfs(int x, int y, int d) {
		if (x == N-1 && y == N-1) {
			answer++;
			return;
		}
		
		switch(d) {
		case 0:	// 가로
			// 가로
			if (y < N-1 && map[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}			
			break;
		case 1:	// 세로
			if (x < N-1 && map[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		case 2:	// 대각선
			if (y < N-1 && map[x][y+1] == 0) {
				dfs(x, y+1, 0);
			}
			if (x < N-1 && map[x+1][y] == 0) {
				dfs(x+1, y, 1);
			}
			break;
		}
		
		if (x < N-1 && y < N-1 && map[x+1][y] == 0 && map[x][y+1] ==0 && map[x+1][y+1] == 0) {
			dfs(x+1, y+1, 2);
		}
	}
}
