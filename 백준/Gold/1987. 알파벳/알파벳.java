import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, answer=0;
	static char[][] board;
	static boolean[] check = new boolean[26];
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		board = new char[R][C];
		for (int i=0; i<R; i++) {
			String s = br.readLine();
			for (int j=0; j<C; j++) {
				board[i][j] = s.charAt(j);
			}
		}
		
		check[board[0][0] - 'A'] = true;
		dfs(0, 0, 1);
		System.out.println(answer);
	}
	
	static void dfs(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);
		
		for (int d=0; d<4; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (0>nx || nx>=R || 0>ny || ny>=C )	continue;
			
			int next = board[nx][ny] - 'A';
			if (!check[next]) {
				check[next] = true;
				dfs(nx, ny, cnt+1);
				check[next] = false;
			}
		}
		
	}
	
}
