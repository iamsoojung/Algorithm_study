import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	public static class Node implements Comparable<Node>{
		int x, y, dist;

		public Node(int x, int y, int dist) {
			super();
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}
	
	static int N, answer, tc=1;
	static int[][] map;
	static int[][] dist;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		while(true) {
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
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0));
		dist[0][0] = map[0][0];
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cx = cur.x;
			int cy = cur.y;
			
			if (dist[cx][cy] < cur.dist)	continue;	// 이미갱신되어있는비용 < 현재보는비용
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				
				if (dist[nx][ny] > dist[cx][cy] + map[nx][ny]) {
					dist[nx][ny] = dist[cx][cy] + map[nx][ny];
					pq.offer(new Node(nx, ny, dist[nx][ny]));
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
