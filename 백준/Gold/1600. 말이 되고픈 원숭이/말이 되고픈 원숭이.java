import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int K, W, H, answer = 0;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] hx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] hy = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main(String[] args) throws IOException {	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());	// 움직일 수 있는 횟수
		
		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());	// 가로
		H = Integer.parseInt(st.nextToken());	// 세로
		
		map = new int[H][W];	// 0 : 아무것도 없는 평지, 1 : 장애물
		for (int i=0; i<H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[H][W][K+1];
		bfs(0, 0);
		
		System.out.println(answer);
	}
	
	static void bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.offer(new Node(x, y, 0, 0));
		visit[x][y][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			
			// 도착한 경우
			if (cx == H-1 && cy == W-1) {
				answer = cur.dist;
				return;
			}
			
			// k 이하로 말처럼 움직였다면
			//		말 처럼 움직이기
			// 상하좌우 움직이기
			if (cur.horseCnt < K) {
				for (int d=0; d<8; d++) {
					int nx = cx + hx[d];
					int ny = cy + hy[d];
					
					if (0<=nx && nx<H && 0<=ny && ny<W && !visit[nx][ny][cur.horseCnt+1]
							&& map[nx][ny] == 0) {
						visit[nx][ny][cur.horseCnt+1] = true;
						q.offer(new Node(nx, ny, cur.dist+1, cur.horseCnt+1));
					}
				}
			}
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (0<=nx && nx<H && 0<=ny && ny<W && !visit[nx][ny][cur.horseCnt]
						&& map[nx][ny] == 0) {
					visit[nx][ny][cur.horseCnt] = true;
					q.offer(new Node(nx, ny, cur.dist+1, cur.horseCnt));
				}
			}
		}
		answer = -1;
		return;
	}
	 
	static class Node {
		int x;
		int y;
		int dist;
		int horseCnt;
		
		public Node(int x, int y, int dist, int horseCnt) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.horseCnt = horseCnt;
		}
	}
}
