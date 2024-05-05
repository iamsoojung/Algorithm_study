
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x, y, dir, cnt;

		public Node(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;		// 명령 카운트
		}
	}
	
	static int M, N, answer=0;
	static Node start, end;
	static int[][] map;
	static boolean[][][] visit;
	static int[] dx = {0, 0, 1, -1};	// 우좌하상
	static int[] dy = {1, -1, 0, 0};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());	// 세로길이, 행
		N = Integer.parseInt(st.nextToken());	// 가로길이, 열
		
		map = new int[M][N];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());	// 0:갈수있음, 1:못감
			}
		}
		
		st = new StringTokenizer(br.readLine());
		start = new Node(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 
				Integer.parseInt(st.nextToken())-1, 0);
		
		st = new StringTokenizer(br.readLine());
		end = new Node(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, 
				Integer.parseInt(st.nextToken())-1, 0);
		
		visit = new boolean[M][N][4];
		System.out.println(bfs());
	}
	
	static int bfs() {
		Queue<Node> q = new ArrayDeque<>();
		q.add(start);
		visit[start.x][start.y][start.dir] = true;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			int cx = cur.x;
			int cy = cur.y;
			int cDir = cur.dir;
			
			if (cx == end.x && cy == end.y && cDir == end.dir) {
				return cur.cnt;
			}
			
			// 앞으로
			for (int c=1; c<=3; c++) {
				int nx = cx + dx[cDir]*c;
				int ny = cy + dy[cDir]*c;
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny][cDir])	continue;
				if (map[nx][ny] == 1)	break;
				
				q.add(new Node(nx, ny, cur.dir, cur.cnt + 1));
				visit[nx][ny][cur.dir] = true;
			}
			
			// 회전
			int left=0, right=0;
			switch(cur.dir) {
			case 0:
				left = 3; right=2;
				break;
			case 1:
				left = 2; right=3;
				break;
			case 2:
				left = 0; right=1;
				break;
			case 3:
				left = 1; right=0;
				break;
			}
			
			if (!visit[cx][cy][left]) {
				visit[cx][cy][left] = true;
				q.add(new Node(cx, cy, left, cur.cnt+1));	
			}
			if (!visit[cx][cy][right]) {
				visit[cx][cy][right] = true;
				q.add(new Node(cx, cy, right, cur.cnt+1));	
			}
		}
		return -1;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<M && 0<=y && y<N;
	}

}