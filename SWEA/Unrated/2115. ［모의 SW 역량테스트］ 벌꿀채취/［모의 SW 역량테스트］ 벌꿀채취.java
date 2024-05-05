import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 두 일꾼의 벌통 고르기 => 조합
// 2. 고른 벌통의 수익 구하기 (최대로 갱신) => 부분집합

public class Solution {

	static int N, M, C, resultA, resultB, answer;
	static int[][] map;
	static boolean[][] selected;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 벌통 크기
			M = Integer.parseInt(st.nextToken()); // 선택 가능한 벌통 개수
			C = Integer.parseInt(st.nextToken()); // 꿀 채취 가능한 최대 양

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			selected = new boolean[N][N];
			visit = new boolean[M];
			resultA = 0;	resultB = 0;
			answer = 0;
			comb();
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}

	static void comb() {

		// 1번 벌통 고르기
		for (int i=0; i<N; i++) {
			for (int j=0; j<=N-M; j++) {
				for (int k=j; k<j+M; k++) {
					selected[i][k] = true;
				}
				
				// 2번 벌통 고르기
				for (int a=0; a<N; a++) {
					for (int b=0; b<=N-M; b++) {
						
						boolean flag = true;
						for (int c=0; c<M; c++) {	// 겹치면 꺼졍
							if (selected[a][b+c]) {
								flag = false;
								break;
							}
						}
						if(!flag)	continue;
						
						subset(0, i, j, a, b);	// 점수 구하기
						answer = Math.max(answer, resultA + resultB);
						resultA = 0;	resultB = 0;
					}
				}

				for (int k=j; k<j+M; k++) {	// 백트래킹
					selected[i][k] = false;
				}
			}
		}
	}
	
	static void subset(int cnt, int i, int j, int a, int b) {
		if (cnt == M) {
			int score1=0, score2=0, weight1=0, weight2=0;
			for (int z=0; z<M; z++) {
				if (visit[z]) {
					score1 += map[i][j+z] * map[i][j+z];
					weight1 += map[i][j+z];
					score2 += map[a][b+z] * map[a][b+z];
					weight2 += map[a][b+z];
				}
			}
			if (weight1 <= C) {
				resultA = Math.max(resultA, score1);
			}
			if (weight2 <= C) {
				resultB = Math.max(resultB, score2);
			}
			return;
		}
		
		visit[cnt] = true;
		subset(cnt+1, i, j, a, b);
		visit[cnt] = false;
		subset(cnt+1, i, j, a, b);
	}
}