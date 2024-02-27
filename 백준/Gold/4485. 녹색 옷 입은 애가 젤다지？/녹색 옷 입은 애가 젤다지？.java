import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node implements Comparable<Node> {
		int x, y, w;

		public Node(int x, int y, int w) {
			super();
			this.x = x;
			this.y = y;
			this.w = w;
		}

		@Override
		public int compareTo(Main.Node o) {
			return Integer.compare(this.w, o.w);
		}
	}
	
	static int N, answer=0, tc=1;
	static int[][] map;
	static int[][] dist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)	break;
			
			map = new int[N][N];
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			dist = new int[N][N];
			for (int i=0; i<N; i++) {
				Arrays.fill(dist[i], Integer.MAX_VALUE);
			}
			
			dijkstra();
			
			answer = dist[N-1][N-1];
			sb.append("Problem ").append(tc).append(": ").append(answer).append("\n");
			tc++;
		}
		System.out.print(sb.toString());
	}
	
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		dist[0][0] = map[0][0];	// 시작 정점 비용 적용
		pq.offer(new Node(0, 0, map[0][0]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cx = cur.x;
			int cy = cur.y;
			int cw = cur.w;
			
			if (dist[cx][cy] < cw)	continue;	// 가봤자 최소가 나올 수 없음
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (0 > nx || nx >= N || 0 > ny || ny >= N)	continue;
				
				if (dist[nx][ny] > dist[cx][cy] + map[nx][ny]) {	// 직빵 > 우회
					dist[nx][ny] = dist[cx][cy] + map[nx][ny];
					pq.offer(new Node(nx, ny, dist[nx][ny]));
				} 
			}
		}
	}
}