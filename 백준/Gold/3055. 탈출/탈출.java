import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// b3055 탈출

public class Main {
	
	static final int WATER = -1;
	static final int EMPTY = 0;	
	static final int DD = 1;	
	
	static class Node {
		int r, c, cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int R, C, endr, endc, answer=0;
	static char[][] map;
	static int[][] visit;	// 0 : 가능, 1 : 못가는곳, -1 : 물
	static Queue<Node> gq = new ArrayDeque<>();		// 고슴도치 큐
	static Queue<Node> wq = new ArrayDeque<>();		// 물 큐
	
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new char[R][C];
		visit = new int[R][C];
		
		String s;
		for (int i=0; i<R; i++) {
			s = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				
				if (map[i][j] == '.')	continue;
				
				if (map[i][j] == 'S') {
					gq.add(new Node(i, j, 0));
					visit[i][j] = DD;
				}
				else if (map[i][j] == 'D') {
					endr = i;
					endc = j;
				}
				else if (map[i][j] == 'X'){
					visit[i][j] = DD;
				} 
				else if (map[i][j] == '*') {
					wq.add(new Node(i, j, 0));
					visit[i][j] = WATER;
				}
			}
		}
		
		bfs();
		
		if (answer == 0) {
			System.out.print("KAKTUS");
		} else {
			System.out.print(answer);
		}
		
	}

	// . 빈곳 / * 물차있는지역 / X 돌 / D 비버의 굴(시작점) / S 고슴도치 위치(도착점)
	static void bfs() {
		while(!gq.isEmpty()) {
			int size = gq.size();
			// 물 퍼트리기
			water();
			for (int i=0; i<size; i++) {
				
				Node cur = gq.poll();
				int cx = cur.r;
				int cy = cur.c;
				
				if (cx == endr && cy == endc) {	// 도착했냐옹 
					answer = cur.cnt;
					return;
				}
				
				for (int d=0; d<4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if(!isValid(nx, ny))	continue;	// 범위 체크
					if (visit[nx][ny] != EMPTY)	continue;	// 방문 체크
					
					// 고슴도치 이동
					gq.add(new Node(nx, ny, cur.cnt+1));
					visit[nx][ny] = DD;
				}
			} 
		}
	}
	
	static void water() {
		int size = wq.size();
		
		for (int i=0; i<size; i++) {
			Node cur = wq.poll();
			int cx = cur.r;
			int cy = cur.c;
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];

				if(!isValid(nx, ny))	continue;	// 범위 체크
				if (visit[nx][ny] != EMPTY)	continue;	// 방문 체크
				if (nx == endr && ny == endc)	continue;	// 물은 소굴로 못 감
				
				wq.add(new Node(nx, ny, 0));
				visit[nx][ny] = WATER;
			}			
		}
	}
	
	static boolean isValid(int x, int y) {
		if (0>x || x>=R || 0>y || y>=C) {
			return false;
		}
		return true;
	}
}