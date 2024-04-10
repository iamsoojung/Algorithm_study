import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
	
	static int N, M, answer=0;	// 공간의 크기 (2 ~ 50)
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static int[][] map, dist;
	static Queue<Node> q = new ArrayDeque<>();
	
	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		dist = new int[N][M];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				
				if (map[i][j] == 1) {	// 상어 있는 곳으로부터 거리 저장하기 위함
					q.add(new Node(i, j));
					dist[i][j] = 0;
				}
			}
		}
		
		bfs(0, 0);
		
		System.out.println(answer);
	}
	
	static boolean isValidCoord(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
	
	static void bfs(int x, int y) {
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			for (int d=0; d<8; d++) {
				int nX = cur.x + dx[d];
				int nY = cur.y + dy[d];
				
				if (isValidCoord(nX, nY)) {		// 범위 내에 있는 경우
					if (dist[nX][nY] == 0 && map[nX][nY] != 1) {	// 거리 저장 안됐거나 && 상어 위치 아님
						dist[nX][nY] = dist[cur.x][cur.y] + 1;
						answer = Math.max(answer, dist[nX][nY]);
						q.add(new Node (nX, nY));
					}
				}
			}
		}
	}
}