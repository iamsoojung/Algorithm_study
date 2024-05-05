import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 1. 두 일꾼의 벌통 고르기 => 조합
// 2. 고른 벌통의 수익 구하기 (최대로 갱신) => 부분집합

public class Solution {

	static int N, M, C, score, answer;
	static int[][] map;

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

			answer = 0;
			comb(0, 0, 0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}

	static void comb(int cnt, int start, int sum) {
		if (cnt == 2) {		// 두명꺼 다 구했다면
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i=start; i<N*N; i++) {
			int x = i/N;
			int y = i%N;
			
			if (y+M <= N) {	// 열이 크기 초과하지 않았을 때
				score = 0;
				subset(0, x, y, 0, 0);
				comb(cnt + 1, i + M, sum + score);
			}
		}
	}
	
	static void subset(int cnt, int x, int y, int weightSum, int scoreSum) {
		if (weightSum > C) 	return;
		if (cnt == M) {	// M개벌통 부분집합 다 한 경우
			score = Math.max(score, scoreSum);
			return;
		}
		subset(cnt+1, x, y, weightSum + map[x][y+cnt], scoreSum + map[x][y+cnt]*map[x][y+cnt]);
		subset(cnt+1, x, y, weightSum, scoreSum);		
	}
}
