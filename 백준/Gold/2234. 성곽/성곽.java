import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[][] map;
	static int[][] visit;		// 방 번호 저장할거임 (0이면 미방문)
	
	static Map<Integer, Integer> room = new HashMap<>();
	static int maxRoom, brokenMaxRoom;

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		visit = new int[M][N];
		bfs();
		brokenBfs();

		sb.append(room.size()).append("\n");	// 이 성에 있는 방의 개수
		sb.append(maxRoom).append("\n");		// 가장 넓은 방 넓이
		sb.append(brokenMaxRoom);				// 하나 벽을 제거해서 얻을 수 있는 가장 넓은 방 크기
		
		System.out.print(sb.toString());
	}

	static void bfs() {
		int roomNum = 0; // 방번호 1부터 시작
		int roomSize = 0;

		Queue<int[]> q = new ArrayDeque<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visit[i][j] == 0) {
					visit[i][j] = ++roomNum;
					roomSize = 1;

					q.offer(new int[] { i, j });
					while (!q.isEmpty()) {
						int[] cur = q.poll();
						int cx = cur[0];
						int cy = cur[1];

						for (int d = 0; d < 4; d++) {
							int nx = cx + dx[d];
							int ny = cy + dy[d];

							if (!inRange(nx, ny))
								continue;
							
							// 벽 없음 & 미방문인 경우
							if ((map[cx][cy] & (1<<d)) == 0 && visit[nx][ny] == 0) {
								visit[nx][ny] = visit[cx][cy];
								q.offer(new int[] {nx, ny});
								
								roomSize++;
							}
						}
					}
					
					room.put(roomNum, roomSize);	// 방 저장
					maxRoom = Math.max(maxRoom, roomSize);	// 최대 방사이즌지
				}
			}
		}
	}
	
	static void brokenBfs() {
		for (int i=0; i<M; i++) {
			for (int j=0; j<N; j++) {
				int curNum = visit[i][j];
				
				for (int d=0; d<4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					
					if (!inRange(nx, ny))	continue;
					
					if (visit[nx][ny] != curNum) {	// 인접이 다른 방이면
						brokenMaxRoom = Math.max(brokenMaxRoom, 
								room.get(curNum) + room.get(visit[nx][ny]));
					}
				}
			}
		}
	}

	static boolean inRange(int x, int y) {
		return 0 <= x && x < M && 0 <= y && y < N;
	}
}
