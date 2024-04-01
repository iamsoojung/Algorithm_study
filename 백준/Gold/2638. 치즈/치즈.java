import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, cheeseCnt=0, answer=0;
	static int[][] map;
	static int[][] cnt;		// -1 : 방문 O, 0 : 방문 X, 1이상 : 주위 공기 카운트
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String args[]) throws IOException {		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1) {
					cheeseCnt++;
				}
			}
		}
		
		while(cheeseCnt > 0) {
			cnt = new int[N][M];
			
			answer++;
			bfs();
		}
		
		System.out.println(answer);
	}
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {0, 0});
		cnt[0][0] = -1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (cnt[nx][ny] == -1)		continue;
								
				if (map[nx][ny] == 0) {	// 다음 칸이 공기면
					cnt[nx][ny] = -1;	// 방문 처리
					q.add(new int[] {nx, ny});	// 큐에 넣기 (다음 탐색)
				} else {	// 다음 칸이 치즈면
					cnt[nx][ny]++;		// 공기 세기
				}
			}			
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<M; j++) {
				if (cnt[i][j] >= 2) {
					cheeseCnt--;
					map[i][j] = 0;
				}
			}
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<M;
	}
}
