import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, K, answer;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 도시의 크기
			M = Integer.parseInt(st.nextToken());	// 한 집이 지불하는 비용
			
			map = new int[N][N];	// 1: 집, 0: 나머지
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			answer = 0;
			for (int i=0; i<N; i++) {
				for (int j=0; j<N; j++) {
					visit = new boolean[N][N];
					bfs(i, j);
				}
			}
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void bfs(int startX, int startY) {
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startX, startY));
		visit[startX][startY] = true;
		
		int K = 1;	// 운영 영역의 크기, 1부터 시작 (점점 늘리기)
		int cnt = 0;	// 포함된 집 갯수
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			for (int i=0; i<size; i++) {
				Node cur = q.poll();
				
				if (map[cur.x][cur.y] == 1)	cnt++;
				
				for (int d=0; d<4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (!inRange(nx, ny))	continue;
					if (visit[nx][ny])	continue;
					
					q.add(new Node(nx, ny));
					visit[nx][ny] = true;
				}
			}
			
			int cost = cnt*M - (K*K + (K-1)*(K-1));
			if (cost >= 0) {
				answer = Math.max(answer, cnt);
			}
			
			K++;
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
