import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x, y, dist, crushCnt;
		boolean time;	// 낮 T, 밤 F (낮부터 시작, 낮에만 벽 부수기 가능)

		public Node(int x, int y, int dist, int crushCnt, boolean time) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.crushCnt = crushCnt;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", dist=" + dist + ", crushCnt=" + crushCnt + ", time=" + time + "]";
		}
	}
	
	static int N, M, K;
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
		K = Integer.parseInt(st.nextToken());	// 부술 수 있는 벽 개수
		
		map = new int[N][M];
		visit = new boolean[N][M][K+1];

		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		
		int answer = bfs(0, 0);	// (0,0) 부터 시작
		
		System.out.println(answer);

	}
	
	static int bfs(int x, int y) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(x, y, 1, 0, true));
		visit[x][y][0] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			
			if (cx == N-1 && cy == M-1)	{
				return cur.dist;
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				
				// 벽 X
				if (map[nx][ny] == 0) {
					if (!visit[nx][ny][cur.crushCnt]) {
						q.offer(new Node(nx, ny, cur.dist+1, cur.crushCnt, !cur.time));
						visit[nx][ny][cur.crushCnt] = true;
					}
				}
				
				// 벽 O
				else if (map[nx][ny] == 1) {
					if (cur.crushCnt < K && !visit[nx][ny][cur.crushCnt+1]) {
						if (cur.time) {	// 부수기
							q.offer(new Node(nx, ny, cur.dist+1, cur.crushCnt+1, !cur.time));
							visit[nx][ny][cur.crushCnt+1] = true;
						} else {	// 머무르기
							q.offer(new Node(cx, cy, cur.dist+1, cur.crushCnt, !cur.time));
						}
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
