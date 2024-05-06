
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static boolean flag;	// 파이프 잘 놓았는지 
	static int R, C, answer=0;
	static char[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1};	// 그리디........
	static int[] dy = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		for (int i=0; i<R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		
		for (int i=0; i<R; i++) {
			flag = false;
			dfs(i, 0);
		}
		System.out.print(answer);
	}
	
	static void dfs(int x, int y) {
		if (y == C-1) {
			flag = true;
			answer++;
			return;
		}
		
		for (int d=0; d<3; d++) {
			int nx = x + dx[d];
			int ny = y + dy[d];
			
			if (!inRange(nx, ny))	continue;
			if (map[nx][ny] != '.')	continue;
			
			map[nx][ny] = '-';	// 파이프 놓기
			dfs(nx, ny);
			
			if (flag)	return;		// 벌써 파이프 놓는 방법을 찾았다면, 더 볼 필요 X
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<R && 0<=y && y<C;
	}
}