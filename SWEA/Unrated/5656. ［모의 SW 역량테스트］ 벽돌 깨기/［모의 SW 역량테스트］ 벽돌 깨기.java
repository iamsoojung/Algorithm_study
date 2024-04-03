import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
	
	static class Point{	// 큐에 넣을 객체를 위한 클래스
		int r, c, cnt;	// 행, 열, 벽돌 숫자
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	static int dr[] = {-1, 1, 0, 0};
	static int dc[] = {0, 0, -1, 1};
	
	static int N, W, H, min;

	public static void main(String[] args) throws IOException{
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringBuilder sb = new StringBuilder();
		 int t = Integer.parseInt(br.readLine());
		 
		 for (int testCase=1; testCase<=t; testCase++) {
			 StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			 N = Integer.parseInt(st.nextToken());	// 구슬 던지는 횟수
			 W = Integer.parseInt(st.nextToken());	// 가로(열의 크기)
			 H = Integer.parseInt(st.nextToken());	// 세로(행의 크기)
			 
			 int[][] map = new int[H][W];
			 for (int i=0; i<H; i++) {
				 st = new StringTokenizer(br.readLine(), " ");
				 for (int j=0; j<W; j++) {
					 map[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 min = Integer.MAX_VALUE;
			 go(0, map);
			 
			 sb.append("#").append(testCase).append(" ").append(min).append("\n");
		 }
		 System.out.println(sb.toString());
	}
	
	// 구슬 던지기 : 던져서 벽돌이 모두 깨졌으면 true 아니면 false 리턴
	private static boolean go(int count, int[][] map) {
		
		int remainCnt = getRemain(map);
		if(remainCnt == 0) {
			min = 0;
			return true;	// 다 깨진 상황의 true 리턴
		}
		
		if(count == N) {
			min = Math.min(min, remainCnt);
			return false;	// 다 깨졌는지 여부 리턴
		}
		
		// 모든 열에 던지기 시도
		int[][] newMap = new int[H][W];	// 사용할 새로운 map
		for (int c=0; c<W; c++) {	// 열 고정
			// 구슬에 맞는 가장 윗벽돌 찾기
			int r = 0;
			while(r < H && map[r][c] == 0)	++r;
			
			if(r == H)	continue;	// 맞는 벽돌이 없으므로 다음 열에 던지기 시도
			
			// 벽돌 깨기 전, 벽돌 복사
			copy(map, newMap);
			
			// 해당 벽돌 깨트리기
			int brick = newMap[r][c];

			// 연쇄 벽돌 처리
			boom(r, c, newMap);
			
			if (brick > 1) {
				// 중력 작용 처리
				down(newMap);
			}
			
			// 다음 구슬 던지러 가기
			if(go(count+1, newMap))	return true;
		}
		return false;
	}
	
	private static void boom(int r, int c, int[][] map) {
		Queue<Point> q = new ArrayDeque<>();
		// 방문 처리 후 큐에 넣기
		if (map[r][c] > 1) {
			q.offer(new Point(r, c, map[r][c]));
		}
		map[r][c] = 0;	// 방문 처리 (벽돌 깨기)
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			
			// 현재 벽돌에 쓰인 수-1 만큼 4방 탐색
			for (int d=0; d<4; d++) {
				int nr = cur.r;
				int nc = cur.c;
				for (int k=1; k<cur.cnt; k++) {
					nr += dr[d];
					nc += dc[d];
					
					if (0<=nr && nr<H && 0<=nc && nc<W && map[nr][nc]>0) {
						if (map[nr][nc] > 1) {
							q.offer(new Point(nr, nc, map[nr][nc]));
						}
						map[nr][nc] = 0;	// 방문처리(벽돌 깨기)
					}
				}
			}	
		}	
	}
	
	private static Stack<Integer> stack = new Stack<>();
	private static void down(int[][] map) {		
		for (int c=0; c<W; c++) {
			for (int r=0; r<H; r++) {	// 윗행부터 깨지지 않은 벽돌 스택에 담기
				if (map[r][c] == 0)	continue;
				stack.push(map[r][c]);
				map[r][c] = 0;
			}
			int r = H-1;
			while(!stack.isEmpty()) {
				map[r--][c] = stack.pop();
			}
		}
	}
	
	private static int getRemain(int[][] map) {
		int cnt = 0;
		for (int r=0; r<H; r++) {
			for (int c=0; c<W; c++) {
				if (map[r][c] > 0)	++cnt;
			}
		}
		return cnt;
	}
	
	
	private static void copy(int[][] map, int[][] newMap) {
		for (int r=0; r<H; r++) {
			for (int c=0; c<W; c++) {
				newMap[r][c] = map[r][c];
			}
		}
	}

}
