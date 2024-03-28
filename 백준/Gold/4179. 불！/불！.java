import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int r, c, cnt;
		
		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int R, C, answer=0;
	static char[][] map;
	static Queue<Node> jihun = new ArrayDeque<>();
	static Queue<Node> fire = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// #: 벽
	// .: 지나갈 수 있는 공간
	// J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간)
	// F: 불이 난 공간
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		
		String s;
		for (int i=0; i<R; i++) {
			s = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				
				if (map[i][j] == 'J') {
					jihun.add(new Node(i, j, 1));
					
				} else if (map[i][j] == 'F') {
					fire.add(new Node(i, j, 1));
				}
			}
		}
		
		bfs();
		
		System.out.print(answer == 0 ? "IMPOSSIBLE" : answer);
	}
	
	static void bfs() {
		int size;
		while(!jihun.isEmpty()) {
			// 불
			size = fire.size();
			for (int i=0; i<size; i++) {
				Node cur = fire.poll();
				int cx = cur.r;
				int cy = cur.c;
				
				for (int d=0; d<4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if (!isValid(nx, ny))	continue;	// 범위 체크
					
					if (map[nx][ny] == '#')	continue;	// 벽
					if (map[nx][ny] == 'J')	continue;	// 지훈
					
					if (map[nx][ny] == '.') {
						map[nx][ny] = 'F';
						fire.add(new Node(nx, ny, 1));
					}
				}
			}
			
			// 지훈이 이동
			size = jihun.size();
			for (int i=0; i<size; i++) {
				Node cur = jihun.poll();
				int cx = cur.r;
				int cy = cur.c;
				
				if (checkEscape(cx, cy)) {
					answer = cur.cnt;
					return;
				}
				
				for (int d=0; d<4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if (!isValid(nx, ny))	continue;	// 범위 체크
					
					if (map[nx][ny] == '#')	continue;	// 벽
					if (map[nx][ny] == 'F')	continue;	// 불
					
					if (map[nx][ny] == '.') {
						if (checkEscape(nx, ny)) {
							answer = cur.cnt + 1;
							return;
						}
						map[nx][ny] = 'J';
						jihun.add(new Node(nx, ny, cur.cnt+1));
					}
					
				}
			}
		}
	}
	
	static boolean checkEscape(int r, int c) {
		if (r == 0 || c == 0 || r == R-1 || c == C-1) {
			return true;
		}		
		return false;
	}
	
	static boolean isValid(int r, int c) {
		if (0>r || r>=R || 0>c || c>=C) {
			return false;
		} return true;
	}

}
