import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x, y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int R, C;
	static int time, cnt;	// 녹이는데 걸리는 시간, 녹인 치즈 조각 갯수
	static int[][] cheese;
	static ArrayList<Node> pick = new ArrayList<>(); // 녹일 치즈 좌표 저장
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		cheese = new int[R][C];
		for (int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<C; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		time = 0;
		while(true) {
			pick.clear();	// 매번 테두리 치즈만 녹이기 때문에 clear
			
			visit = new boolean[R][C];
			dfs(0, 0);
			
			cnt = 0;
			for (Node p : pick) {	// 녹이기 작업
				cheese[p.x][p.y] = 0;
				cnt++;
			}
			
			boolean flag = true;
			for (int i=0; i<R; i++) {
				for (int j=0; j<C; j++) {
					if (cheese[i][j] != 0) {	// 다 녹았는지?
						flag = false;
					}
				}
			}
			time++;
			if (flag)	break;
		}
		sb.append(time).append("\n").append(cnt);
		System.out.print(sb);
	}
	
	static void dfs(int x, int y) {
		visit[x][y] = true;
		
		if (cheese[x][y] == 1) {
			pick.add(new Node(x, y));
			return;
		}
		
		for (int i=0; i<4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if (0<=nx && nx<R && 0<=ny && ny<C && !visit[nx][ny]) {
				dfs(nx, ny);
			}
		}
	}
}