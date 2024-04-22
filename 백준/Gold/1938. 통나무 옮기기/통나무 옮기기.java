import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 1 : 나무, 0 : 빈곳
// 기차 길이는 항상 3, B개수 == E개수
// 빈곳(0)에만 기차 이동 가능
// 회전 시, 3*3 구역이 모두 0이여야 함

public class Main {

	static class Node {
		int x, y, dist, pos;

		public Node(int x, int y, int dist, int pos) {
			this.x = x; // 중심점 좌표
			this.y = y;
			this.dist = dist;
			this.pos = pos; // 가로 0, 세로 1
		}
	}

	static int N, answer = 0;
	static int[][] B = new int[3][2];
	static int[][] E = new int[3][2];
	static char[][] map;
	static boolean[][][] visit;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		map = new char[N][N];

		String s;
		int bIdx = 0, eIdx = 0;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = s.charAt(j);
				if (map[i][j] == 'B') {
					B[bIdx][0] = i;
					B[bIdx++][1] = j;
				} else if (map[i][j] == 'E') {
					E[eIdx][0] = i;
					E[eIdx++][1] = j;
				}
			}
		}

		int bPos = -1;
		if (B[0][0] == B[2][0])
			bPos = 0;
		else
			bPos = 1;

		int ePos = -1;
		if (E[0][0] == E[2][0])
			ePos = 0;
		else
			ePos = 1;
		
		visit = new boolean[N][N][2];
		answer = bfs(bPos, ePos);
		System.out.println(answer);

	}

	static int bfs(int bPos, int ePos) {	// 1, 0
		Queue<Node> q = new ArrayDeque<>();
		q.add(new Node(B[1][0], B[1][1], 0, bPos));
		visit[B[1][0]][B[1][1]][bPos] = true; // 중심점 visit 처리

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (E[1][0] == cur.x && E[1][1] == cur.y && ePos == cur.pos) {
				return cur.dist;
			}

			// 상하좌우
			for (int d = 0; d < 4; d++) {
				int nx = cur.x + dx[d];
				int ny = cur.y + dy[d];

				if (!inRange(nx, ny, cur.pos))
					continue;

				if (!goOK(nx, ny, cur.pos))	continue;
				if (visit[nx][ny][cur.pos])	continue;

				q.add(new Node(nx, ny, cur.dist + 1, cur.pos));
				visit[nx][ny][cur.pos] = true;
			}
			
			if (checkSquare(cur.x, cur.y)) {
				if (cur.pos == 0) { // 현재 가로
					if (!visit[cur.x][cur.y][1]) {
						q.add(new Node(cur.x, cur.y, cur.dist+1, 1));
						visit[cur.x][cur.y][1] = true;
					}
				} else { // 현재 세로
					if (!visit[cur.x][cur.y][0]) {
						q.add(new Node(cur.x, cur.y, cur.dist+1, 0));
						visit[cur.x][cur.y][0] = true;
					}
				}
			}
		}
		return 0;
	}

	static boolean checkSquare(int x, int y) {
		if (0 > x - 1 || x + 1 >= N || 0 > y - 1 || y + 1 >= N) {
			return false;
		}

		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (map[i][j] == '1')
					return false;
			}
		}
		return true;
	}

	static void recover(int x, int y, int pos, char ch) {
		if (pos == 0) {
			map[x][y - 1] = ch;
			map[x][y] = ch;
			map[x][y + 1] = ch;
		} else {
			map[x - 1][y] = ch;
			map[x][y] = ch;
			map[x + 1][y] = ch;
		}
	}

	static boolean goOK(int x, int y, int pos) {
		if (pos == 0) { // 가로
			if (map[x][y - 1] != '1' && map[x][y] != '1' && map[x][y + 1] != '1') {
				return true;
			}
			return false;
		} else { // 세로
			if (map[x - 1][y] != '1' && map[x][y] != '1' && map[x + 1][y] != '1') {
				return true;
			}
			return false;
		}
	}

	static boolean inRange(int x, int y, int pos) {
		if (pos == 0) { // 가로
			return 0 <= x && x < N && 0 <= y - 1 && y + 1 < N;
		} else { // 세로
			return 0 <= x - 1 && x + 1 < N && 0 <= y && y < N;
		}
	}
}
