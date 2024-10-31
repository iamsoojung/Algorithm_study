import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 뱀이 나와서 기어다니는데, 사과먹으면 뱀 길이 늘어남
 * 뱀은 이리저리 기어다니다가, 벽 or 자신몸 부딪히면 게임 끝
 * 
 * N*N 맵, 몇몇 칸에는 사과 / 상하좌우끝에는 벽
 * 뱀은 (0,0) 시작, 길이 1, 오른쪽 향함
 * 뱀은 매초마다 이동
 * 조건 1) 먼저 뱀은 몸길이 늘려 머리를 다음칸에 위치함
 * 조건 2) if) 벽 or 자신몸 부딪히면 게임 끝
 * 조건 3) if 이동한 칸 == 사과) 사과 없어짐 & 꼬리 움직이지 X
 * 조건 4) if 이동한 칸 != 사과) 몸길이 줄여서 꼬리 위치한 칸 비워짐 (즉, 몸길이 변하지 X)
 * 
 * 게임이 몇초에 끝나는지 계산
 */
// contains 시 참조를 비교하는데, new int[] {nx, ny}는 true를 반환하지 않음
// => 따라서, int[] 대신 Point 객체 사용하여 해결

public class Main {
	
	static class Order {
		int sec;
		char dir;
		
		public Order(int sec, char dir) {
			this.sec = sec;
			this.dir = dir;
		}
	}
	
	static int N, K, L;
	static int[][] map;
	static Queue<Order> q;
	
	static int[] dx = {-1, 0, 1, 0};	// 위, 오, 아, 왼	(오른쪽 방향)
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());	// 맵 크기 N
		K = Integer.parseInt(br.readLine());	// 사과 개수 K
		
		map = new int[N][N];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			map[x][y] = 1;	// 1: 사과, 0:빈칸
		}
		
		L = Integer.parseInt(br.readLine());	// 뱀의 방향 변환 횟수 L
		
		q = new ArrayDeque<>();
		for (int i=0; i<L; i++) {
			st = new StringTokenizer(br.readLine());
			int sec = Integer.parseInt(st.nextToken());
			char d = st.nextToken().charAt(0);
			q.add(new Order(sec, d));
		}
		
		Deque<Point> snake = new ArrayDeque<>();
		snake.add(new Point(0, 0));
		int dir=1; // 시작 시 오른쪽 향함
		int sec = 0;
		
		while(true) {
			sec++;
			
			Point head = snake.peekFirst();
			int nx = head.x + dx[dir];
			int ny = head.y + dy[dir];
			
			// 종료 조건 (벽 or 자기몸충돌)
			if (!inRange(nx, ny) || (snake.contains(new Point(nx, ny))))	break;
			
			// 조건 3) if 이동한 칸 == 사과) 사과 없어짐 & 꼬리 움직이지 X
			// 조건 4) if 이동한 칸 != 사과) 몸길이 줄여서 꼬리 위치한 칸 비워짐 (즉, 몸길이 변하지 X)
			if (map[nx][ny] == 1) {
				map[nx][ny] = 0;
			} else {
				snake.pollLast();
			}
			
			// 새로운 머리 넣기
			snake.addFirst(new Point(nx, ny));
			
			// 게임 종료 후 방향 전환
			if (!q.isEmpty() && sec == q.peek().sec) {
				Order o = q.poll();
				if (o.dir == 'L') {	// 왼쪽으로 90도 회전
					dir = (dir+3) % 4;
				} else if (o.dir == 'D') {	// 오른쪽으로 90도 회전
					 dir = (dir+1) % 4;
				}
			}
		}
		
		System.out.println(sec);
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
}