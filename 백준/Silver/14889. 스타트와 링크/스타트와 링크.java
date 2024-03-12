import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	
	static int N;
	static int[][] ability;
	static boolean[] visit;
	static int min = Integer.MAX_VALUE;	// 답 : 능력치 차이의 최소
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		ability = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				ability[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		visit = new boolean[N];
		comb(0, 0);
		
		System.out.println(min);
	}
	
	static void comb(int depth, int idx) {
		
		if (depth == N/2) {	// 기저 조건 : 팀 다 나누었을 때
			diff();
			return;
		}
		
		for (int i=idx; i<N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				comb(depth+1, i+1);
				visit[i] = false;
			}
		}
	}
	
	static void diff() {
		int startTeam = 0;
		int endTeam = 0;
		
		// 각 팀의 능력치 계산
		for (int i=0; i<N-1; i++) {
			for (int j=i+1; j<N; j++) {
				if (visit[i] && visit[j]) {
					startTeam += ability[i][j] + ability[j][i];
				}
				else if (!visit[i] && !visit[j]) {
					endTeam += ability[i][j] + ability[j][i];
				}
			}
		}

		// 최솟값과 비교하여 갱신
		int diffScore = Math.abs(startTeam-endTeam);
		if (diffScore == 0) {	// 차이가 없다면 0이 곧 차이의 최솟값이므로 그대로 종료
			System.out.println(diffScore);
			System.exit(0);
		}
		min = Math.min(min, diffScore);	
	}
}