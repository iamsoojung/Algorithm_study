import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static class Node {
		int x, y, dist;

		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static int N, M, W, startX, startY, answer;
	static int startW, startIdx;	// 시작할 손님 인덱스
	static int[][] map;
	static int[][] cInfo;
	static boolean[] visitCustomer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());		
		N = Integer.parseInt(st.nextToken());	// 맵 크기
		M = Integer.parseInt(st.nextToken());	// 손님 수
		W = Integer.parseInt(st.nextToken());	// 초기 연료의 양
		map = new int[N][N];
		
		// 0 : 빈칸, 1 : 벽
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		startX = Integer.parseInt(st.nextToken()) - 1;	// 시작 지점
		startY = Integer.parseInt(st.nextToken()) - 1;
		
		cInfo = new int[M][4];
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			cInfo[i][0] = Integer.parseInt(st.nextToken()) - 1;
			cInfo[i][1] = Integer.parseInt(st.nextToken()) - 1;
			cInfo[i][2] = Integer.parseInt(st.nextToken()) - 1;
			cInfo[i][3] = Integer.parseInt(st.nextToken()) - 1;
		}
		
		visitCustomer = new boolean[M];
		for (int i=0; i<M; i++) {
			findCustomer();
			if (answer == -1)	break;
			moveTaxi();
			if (answer == -1)	break;
		}
		System.out.println(answer == -1 ? -1 : W);
	}
	
	static void moveTaxi() {
		boolean[][] visit = new boolean[N][N];
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startX, startY, 0));
		visit[startX][startY] = true;
		W -= startW;
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.x == cInfo[startIdx][2] && cur.y == cInfo[startIdx][3]) {
	            W += cur.dist;
	            startX = cur.x;
	            startY = cur.y;
	            return;
	        }
	        
	        // 연료가 부족하더라도 최대한 이동
	        if (cur.dist + 1 > W) {
	            continue;
	        }
			
			for (int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (!inRange(nx, ny))	continue;				
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 1)	continue;	// 벽
				
				q.add(new Node(nx, ny, cur.dist+1));
				visit[nx][ny] = true;
			}
		}
		answer = -1;
		return;
	}
	
	static void findCustomer() {
		startW = Integer.MAX_VALUE;
		startIdx = -1;
		int d = -1;
		for (int i=0; i<M; i++) {
			if (!visitCustomer[i]) {
				d = getStart(i);
				if (d == -1) continue;
				if (d < startW) {
					startW = d;
					startIdx = i;
				} else if (d == startW) {
					if (cInfo[i][0] < cInfo[startIdx][0]) {
						startW = d;
						startIdx = i;
					} else if (cInfo[i][0] == cInfo[startIdx][0]
							&& cInfo[i][1] < cInfo[startIdx][1]) {
						startW = d;
						startIdx = i;
					}
				}
			}
		}
		if (d == -1) {
			answer = -1;
			return;
		}
		startX = cInfo[startIdx][0];
		startY = cInfo[startIdx][1];
		visitCustomer[startIdx] = true;
//		System.out.println("시작좌표 : " + startX + " " + startY);
		return;
	}
	
	static int getStart(int idx) {
		boolean[][] visit = new boolean[N][N];
		
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(startX, startY, 0));
		
		while(!q.isEmpty()) {
			Node cur = q.poll();
			
			if (cur.dist > startW) {	// 더 봐서 뭐하냐
				return cur.dist;
			}
			
			if (cur.x == cInfo[idx][0] && cur.y == cInfo[idx][1]) {
				return cur.dist;
			}
			
			for (int d=0; d<4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];
				
				if (!inRange(nx, ny))	continue;				
				if (visit[nx][ny])	continue;
				if (map[nx][ny] == 1)	continue;	// 벽
				
				q.add(new Node(nx, ny, cur.dist+1));
				visit[nx][ny] = true;
			}
		}
		return -1;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}
