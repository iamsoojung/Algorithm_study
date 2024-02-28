import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M, R, C, D, answer=1;	// 처음에 빈 칸은 전부 청소되지 않은 상태
	static int[][] map;
	static int[] dr = {-1, 0, 1, 0};	// 시계 방향 입력
	static int[] dc = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 방의 크기
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());	// 시작 좌표
		C = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());	// 시작 방향
		
		map = new int[N][M];	// 0(청소 X), 1(벽), 2(청소 O)
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(R, C, D);
		
		System.out.print(answer);
	}
	
	static void dfs(int r, int c, int d) {
		map[r][c] = -1;

		for (int i=0; i<4; i++) {
			d = (d+3)%4;	// 반시계 방향 회전 (90, 180, 270, 360(본인))
			
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (0<=nr && nr<N && 0<=nc && nc<M) {	
				// 청소할 칸이 있음(0) -> 회전 전진
				if (map[nr][nc] == 0) {
					answer++;
					dfs(nr, nc, d);
					return;
				}
			}
		}
		
		// 청소할 칸이 없음 (!0)
			//-> 한칸 후진 가능(!1) -> 1번 돌아가기 
			//-> 한칸 후진 불가능(1) -> 종료
		int backD = (d+2)%4;
		int nr = r + dr[backD];
		int nc = c + dc[backD];
		
		if (0<=nr && nr<N && 0<=nc && nc<M) {
			if (map[nr][nc] != 1) {	
				 dfs(nr, nc, d);
			}
		}
		return;
	}
}
