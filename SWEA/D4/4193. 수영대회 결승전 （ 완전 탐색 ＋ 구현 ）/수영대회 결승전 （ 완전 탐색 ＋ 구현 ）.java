import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node {
		int x, y, cnt;

		public Node(int x, int y, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
	}

	static int N, A, B, C, D, time=0;
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
			visit = new boolean[N][N];
			
			// 0 : 지나갈 수 있는 곳 , 1 : 장애물 , 2: 주기가 2초인 소용돌이
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());
			
			bfs();
			
			sb.append("#").append(testCase).append(" ").append(time).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(A, B, 0));
		visit[A][B] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			
			if (cx == C && cy == D) {
				time = cur.cnt;
				return;
			}
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny) || map[nx][ny] == 1 || visit[nx][ny])	continue;
				
				if(map[nx][ny] == 2) {	// 소용돌이 만나면
					if (cur.cnt%3 == 2) {	// 소용돌이 사라질 때 !!
						q.add(new Node(nx, ny, cur.cnt+1));
						visit[nx][ny] = true;		
					} else {	// 소용돌이 존재할 때
						q.add(new Node(cx, cy, cur.cnt+1));
						visit[cx][cy] = true;
					}						
				} else {	// 지나갈 수 있음
					q.add(new Node(nx, ny, cur.cnt+1));
					visit[nx][ny] = true;					
				}
			}
		}
		time = -1;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}