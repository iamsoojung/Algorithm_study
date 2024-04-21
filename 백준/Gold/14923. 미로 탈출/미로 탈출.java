import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x, y, dist, broken;	// 0 : 부숨 X , 1 : 부숨 O
		
		public Node(int x, int y, int dist, int broken) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.broken = broken;
		}
	}
	
	static int N, M, startX, startY, endX, endY, answer;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;
		startY = Integer.parseInt(st.nextToken()) - 1;
		
		st = new StringTokenizer(br.readLine());
		endX = Integer.parseInt(st.nextToken()) - 1;
		endY = Integer.parseInt(st.nextToken()) - 1;
		
		// 0이 빈 칸, 1이 벽
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N][M][2];
		answer = Integer.MAX_VALUE;
		bfs();
		
		System.out.print(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startX, startY, 0, 0));
		visit[startX][startY][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;

			if (cx == endX && cy == endY) {
				answer = Math.min(answer, cur.dist);
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if(!inRange(nx, ny))	continue;
				
				if (map[nx][ny] == 1 && cur.broken == 0) {	// 벽 & 부수기 가능
					q.add(new Node(nx, ny, cur.dist+1, 1));
					visit[nx][ny][1] = true;
				} else if (map[nx][ny] == 0 && !visit[nx][ny][cur.broken]) {	// 빈칸 (방문아직안한경우)
					q.add(new Node(nx, ny, cur.dist+1, cur.broken));
					visit[nx][ny][cur.broken] = true;
				}
			}
		}
		return;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}