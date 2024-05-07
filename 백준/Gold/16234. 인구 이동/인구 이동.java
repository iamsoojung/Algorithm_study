import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, L, R;
	static int[][] map;
	static int[][] visit;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	// 연합된 칸의 인구수 : (연합 총 인구수) / (연합 이루는 칸 개수) -> 소수점 버림
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken()); // 인구차이가 L명 이상,
		R = Integer.parseInt(st.nextToken()); // R명 이하 라면 국경선 열기

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int day = 0;
		while (true) {
			int cnt = 0;
			visit = new int[N][N]; // 0 : 방문 x, 1 : 방문 o, -1 : 제외
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] == 0) {
						if (!bfs(i, j))	cnt++;
					}
				}
			}
			
			if (cnt == N*N)	break;
			else	day++;
		}
		
		System.out.println(day);
	}

	static boolean bfs(int startX, int startY) {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] { startX, startY });
		visit[startX][startY] = 1;

		int kanCnt = 1;
		int peopleCnt = map[startX][startY];
		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];

				if (!inRange(nx, ny))
					continue;
				if (visit[nx][ny] != 0)
					continue;

				int diff = Math.abs(map[cur[0]][cur[1]] - map[nx][ny]);
				if (L <= diff && diff <= R) {
					q.add(new int[] { nx, ny });
					visit[nx][ny] = 1;
					kanCnt++;
					peopleCnt += map[nx][ny];
				}
			}
		}

		if (kanCnt > 1) {
			int newPeopleCnt = peopleCnt / kanCnt;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visit[i][j] == 1) {
						map[i][j] = newPeopleCnt;
						visit[i][j] = -1;
					}
				}
			}
			return true;
		} else {
			visit[startX][startY] = -1;
			return false;
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}

}
