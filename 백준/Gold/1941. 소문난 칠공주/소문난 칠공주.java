import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// 1:33 ~
// 단순 bfs 돌리려니 경우가 너무 많음
// 조합 + 연결된지확인bfs 다시 풀었다
public class Main {
	
	static int answer=0;
	static char[][] map = new char[5][5];
	static boolean[] visit;
	static int[] select;	// 25칸 중 선택된 자리 저장
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 5*5 자리 배치
		// 7명 여학생, 서로 인접, 7명 중 4명 이상은 이다솜파(S)여야 함
		// 소문난 칠공주 결성 가능한 모든 경우의 수
		
		for (int i=0; i<5; i++) {
			String s = br.readLine();
			for (int j=0; j<5; j++) {
				map[i][j] = s.charAt(j);
			}
		}
		
		select = new int[7];
		comb(0, 0, 0);
		
		System.out.print(answer);
	}
	
	static void comb(int depth, int start, int yCnt) {
		if (yCnt > 3)	return;
		
		if (depth == 7) {
			visit = new boolean[7];
			check();	// 모두 연결되어있는지
			return;
		}
		
		for (int i=start; i<25; i++) {
			select[depth] = i;
			if (map[i/5][i%5] == 'S') {	// 이다솜파
				comb(depth+1, i+1, yCnt);
			} else {	// 이상한애들
				comb(depth+1, i+1, yCnt+1);
			}
		}
	}
	
	static void check() {
		Queue<int[]> q = new ArrayDeque<>();
		q.add(new int[] {select[0]/5, select[0]%5});
		visit[0] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d=0; d<4; d++) {
				int nx = cur[0] + dx[d];
				int ny = cur[1] + dy[d];
				int nIdx = nx*5 + ny;
				
				if (!inRange(nx, ny))	continue;
				
				// 중요!!! 탐색한 다음위치가, 아직체크안했고 선택한7개자리중하나인지?
				for (int j=0; j<7; j++) {
					if (!visit[j] && select[j] == nIdx) {
						q.add(new int[] {nx, ny});
						visit[j] = true;
						cnt++;
					}
				}
			}
		}
		
		if (cnt == 7)	answer++;
	}
	
	static boolean inRange(int x, int y) {
		return 0<=x && x<5 && 0<=y && y<5;
	}
}
