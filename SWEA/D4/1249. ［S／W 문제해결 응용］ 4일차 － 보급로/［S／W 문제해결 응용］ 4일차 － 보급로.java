import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Node o) {
			return this.cnt - o.cnt;
		}
	}
	
	static int N, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			String s;
			for (int i=0; i<N; i++) {
				s = br.readLine();
				for (int j=0; j<N; j++) {
					map[i][j] = s.charAt(j) - '0';
				}
			}
			
//			for (int i=0; i<N; i++) {
//				for (int j=0; j<N; j++) {
//					System.out.print(map[i][j]);
//				}System.out.println();
//			}
			
			visit = new boolean[N][N];
			answer = Integer.MAX_VALUE;
			bfs();
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");	// ~~~
		}
		System.out.print(sb.toString());
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	static void bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(0, 0, 0));	// (0,0) 에서 시작
		visit[0][0] = true;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			int cx = cur.x;
			int cy = cur.y;
			
			if (cx == N-1 && cy == N-1) {
//				answer = Math.min(answer, cur.cnt);		// 처음에 딱 찾으면 bfs가 바로 종료해버리나?
				answer = cur.cnt;
				return;
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;		// 범위 체크
				if (visit[nx][ny])		continue;		// 방문 체크
				
				int dist = map[nx][ny];
				
				pq.offer(new Node(nx, ny, cur.cnt + dist));
				visit[nx][ny] = true;
			}
		}
	}
}
