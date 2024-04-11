import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, answer = Integer.MAX_VALUE, empty = 0;
	static int[][] map, copyMap;
	static boolean[][] visit;
	static ArrayDeque<int[]> tmpQ = new ArrayDeque<>();
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 연구소의 크기
		M = Integer.parseInt(st.nextToken());	// 놓을 수 있는 바이러스 개수
		
		// 처음엔 모든 바이러스가 비활성 상태
		// 활성 상태인 바이러스는 상하좌우 인접하게 동시 복제됨
		// 바이러스 M개를 활성 상태로 변경할 것임
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0)	empty++;
			}
		}
		// 0은 빈 칸, 1은 벽, 2는 비활성, 3은 활성
		
		// 1. M개만큼 활성상태 여러 경우 시도 해보기
		dfs(0, 0, 0);
		
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}
	
	static void dfs(int cnt, int si, int sj) {
		if (cnt == M) {
			bfs(empty);		// 2. 퍼뜨려보기
			return;
		}
		
		for (int i=si; i<N; i++) {
			for (int j=sj; j<N; j++) {
				if (map[i][j] == 2) {
					map[i][j] = 3;
					tmpQ.add(new int[] {i, j});	// 1) 활성 바이러스를 큐에 넣기
					dfs(cnt+1, i, j);
					map[i][j] = 2;	// 원복
					tmpQ.pollLast();
				}
			}
			sj = 0;
		}
	}
	
	static void bfs(int tmpCnt) {
		Queue<int[]> q = new ArrayDeque<>(tmpQ);	// 복사가된대!!!!!!!!!!!!!
		visit = new boolean[N][N];
		
		// 2) 맵 카피해놓기
		copyMap = new int[N][N];

		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				switch(map[i][j]) { 
				case 1:	// 벽 (-)
					copyMap[i][j] = -3;
					break;
				case 2:	// 비활성 (*)
					copyMap[i][j] = -2;
					break;
				case 3:	// 활성
					copyMap[i][j] = 0;
					break;
				default:	// 빈칸
					copyMap[i][j] = -1;
					break;
				}	// 양수로는 퍼진 시간을 넣어야함
			}
		}
		
		// 3) 활성 바이러스 퍼뜨리기
		int max = 0;
		L1: while(!q.isEmpty()) {
			int[] cur = q.poll();
			int cx = cur[0];
			int cy = cur[1];
			
			max = Math.max(max, copyMap[cx][cy]);	// 마지막 시간 구하기
			if (max > answer)	return;
			
			if (tmpCnt == 0) {
				break L1;
			}			
			
			for (int d=0; d<4; d++) {
				int nx = cx + dx[d];
				int ny = cy + dy[d];
				
				if (!inRange(nx, ny))	continue;
				if (visit[nx][ny])	continue;
				// 빈칸, 비활성일때만 퍼뜨리기 가능
				// 벽은 퍼뜨리기 X, 활성은 왜또;

				if (copyMap[nx][ny] == -2) {
					copyMap[nx][ny] = copyMap[cx][cy] + 1;
					q.add(new int[] {nx, ny});
					visit[nx][ny] = true;
				}
				else if (copyMap[nx][ny] == -1) {
					copyMap[nx][ny] = copyMap[cx][cy] + 1;
					q.add(new int[] {nx, ny});
					visit[nx][ny] = true;
					tmpCnt--;
				}
			}
		}
		
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				max = Math.max(max, copyMap[i][j]);
			}
		}
		
		// 4) 모두 채워졌는지?
		if (check()) {
			// 5) 최소 시간 갱신하기
			answer = Math.min(answer, max);			
		}
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<N && 0<=y && y<N;
	}
	
	static boolean check() {
		for (int i=0; i<N; i++) {
			for (int j=0; j<N; j++) {
				if (copyMap[i][j] == -1)	return false;
			}
		}
		return true;
	}
}
