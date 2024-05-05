
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int answer = 0;
	static char[][] map = new char[12][6];
	static boolean[][] visit;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 12; i++) {
			String s = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = s.charAt(j);
			}
		}

		visit = new boolean[12][6];

		boolean flag;
		while (true) {
			flag = false;
			visit = new boolean[12][6];

			// 뿌요 터뜨리기
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visit[i][j]) {
						if (pangpang(i, j, map[i][j])) {
							flag = true;
						}
					}
				}
			}

			if (!flag) {
				System.out.print(answer);
				break;
			}
			answer++;

			narak();	// 중.력.
		}
	}

	static void narak() {
		for (int j = 0; j < 6; j++) {
			for (int i = 11; i > 0; i--) {
				if (map[i][j] == '.') {
					for (int k = i - 1; k >= 0; k--) {
						if (map[k][j] != '.') {
							map[i][j] = map[k][j];
							map[k][j] = '.';
							break;
						}
					}
				}
			}
		}
	}

	static boolean pangpang(int i, int j, char ch) {
		Queue<int[]> q = new ArrayDeque<>();
		Queue<int[]> newQ = new LinkedList<>();
		
		q.add(new int[] { i, j });
		newQ.add(new int[] { i, j });
		visit[i][j] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])		continue;
				if (map[nx][ny] == '.')	continue;
				if (ch != map[nx][ny])	continue;

				q.add(new int[] { nx, ny });
				newQ.add(new int[] { nx, ny });
				visit[nx][ny] = true;
			}
		}

		if (newQ.size() >= 4) {
			int size = newQ.size();
			for (int w = 0; w < size; w++) {
				int[] tmp = newQ.poll();
				map[tmp[0]][tmp[1]] = '.';
			}
			return true;
		} else
			return false;
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < 12 && 0 <= y && y < 6;
	}
}