import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static class Node {
		int x, y, time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}
	}
	
	static int w, h, answer;
	static char[][] map;
	static Queue<Node> sanggeun;
	static Queue<Node> fire;
	static int dx[] = {-1, 0, 1, 0};
	static int dy[] = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			sanggeun = new ArrayDeque<>();
			fire = new ArrayDeque<>();
			
			map = new char[h][w];
			for (int i=0; i<h; i++) {
				String s = br.readLine();
				for (int j=0; j<w; j++) {
					map[i][j] = s.charAt(j);
					if (map[i][j] == '@') {
						sanggeun.add(new Node(i, j, 1));
					} else if (map[i][j] == '*') {
						fire.add(new Node(i, j, 0));
					}
				}
			}
			answer = bfs();
			sb.append(answer == -1 ? "IMPOSSIBLE" : answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static int bfs() {
		int size;
		
		while(!sanggeun.isEmpty()) {
			size = fire.size();
			for (int i=0; i<size; i++) {
				Node cur = fire.poll();
				
				for (int d=0; d<4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (!inRange(nx ,ny))	continue;
					if (map[nx][ny] == '#') continue;	// 벽
					if (map[nx][ny] == '*')	continue;	// 이미 불이면 패스
					
					fire.add(new Node(nx, ny, 0));
					map[nx][ny] = '*';	// 불 번짐		
				}
			}
			
			size = sanggeun.size();
			for (int i=0; i<size; i++) {
				Node cur = sanggeun.poll();

				if (checkEscape(cur.x, cur.y)) {	// 탈출 가능한지?
					return cur.time;
				}
				
				for (int d=0; d<4; d++) {
					int nx = cur.x + dx[d];
					int ny = cur.y + dy[d];
					
					if (!inRange(nx ,ny))	continue;
					
					if (map[nx][ny] == '.') {	// 빈곳
						sanggeun.add(new Node(nx, ny, cur.time+1));
						map[nx][ny] = '@';
					}
				}
			}
		}
		return -1;
	}
	
	static boolean checkEscape(int x, int y) {
		return 0==x || x==h-1 || 0==y || y==w-1;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<h && 0<=y && y<w;
	}
}