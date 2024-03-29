import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int r, c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
	
	static int R, C, answer=0;
	static int eR, eC;
	static char[][] map;
	static boolean[][] visitWater, visitSwan;
	static Queue<Node> waterQ = new ArrayDeque<>();
	static Queue<Node> swanQ = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	// '.'은 물 공간, 'X'는 빙판 공간, 'L'은 백조가 있는 공간
	// 물과 접촉한 빙판은 녹음
	// 며칠이 지나야 백조들이 만날 수 있는지?
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visitWater = new boolean[R][C];
		visitSwan = new boolean[R][C];
		
		String s;
		for (int i=0; i<R; i++) {
			s = br.readLine();
			for (int j=0; j<C; j++) {
				map[i][j] = s.charAt(j);
				
				if (map[i][j] == '.') {
					waterQ.add(new Node(i, j));
					visitWater[i][j] = true;
				}				
				else if (map[i][j] == 'L') {
					waterQ.add(new Node(i, j));
					if (swanQ.isEmpty()) {
						swanQ.add(new Node(i, j));
						visitSwan[i][j] = true;
                        map[i][j] = '.';
					} else {
						eR = i;
						eC = j;
                        map[i][j] = '.';
					}
				}
			}
		}
		
		melt();
//		checkMeet();
		
		System.out.println(answer);
		
	}
	
	static void melt() {
		while(!swanQ.isEmpty()) {	// 더 녹일 수 없을 때까지
			
			// 얼음 녹이기
			int size = waterQ.size();
			for (int i=0; i<size; i++) {
				Node cur = waterQ.poll();
				int cx = cur.r;
				int cy = cur.c;
				
				for (int d=0; d<4; d++) {
					int nx = cx + dx[d];
					int ny = cy + dy[d];
					
					if (0>nx || nx>=R || 0>ny || ny>=C || visitWater[nx][ny])	continue;
					if (map[nx][ny] != 'X')	continue;
						
					map[nx][ny] = '.';
					waterQ.add(new Node(nx, ny));
					visitWater[nx][ny] = true;
				}
			}
			answer++;
			
			// 백조 만나는지 확인
			if (checkMeet())	return;
		}
	}
	static boolean checkMeet() {
		Queue<Node> tmpQ = new ArrayDeque<>();
		
		while(!swanQ.isEmpty()) {
			Node cur = swanQ.poll();
			int cx = cur.r;
			int cy = cur.c;
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (0>nx || nx>=R || 0>ny || ny>=C || visitSwan[nx][ny])	continue;
				
				if (map[nx][ny] == 'X') {
					tmpQ.add(new Node(nx, ny));
					visitSwan[nx][ny] = true;
					continue;
				}

				if (nx == eR && ny == eC) {
					return true; 
				}

				visitSwan[nx][ny] = true;
				swanQ.add(new Node(nx, ny));
			}
		}
		swanQ = tmpQ;
		return false;
	}
}