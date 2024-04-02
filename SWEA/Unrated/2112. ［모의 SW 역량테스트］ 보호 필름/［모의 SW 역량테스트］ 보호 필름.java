import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int D, W, K, answer;
	static int[][] map;
	static int[] isSelected;	// 0 : 투입 X, 1 : 특성 A, 2 : 특성 B

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			D = Integer.parseInt(st.nextToken());	// 두께
			W = Integer.parseInt(st.nextToken());	// 가로
			K = Integer.parseInt(st.nextToken());	// 합격 기준
			
			map = new int[D][W];
			for (int i=0; i<D; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			isSelected = new int[D];
			answer = Integer.MAX_VALUE;
			dfs(0, 0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}	
	
	// 약품 투입 경우의 수 구하기 (부분집합)
	// 약품 투입 진행 (재귀)
	// @cnt : 투여 횟수, @idx : 현재 보는 인덱스
	static void dfs(int cnt, int idx) {
		if (idx == D) {	// 경우의 수 완성
			if (check()) {
				answer = Math.min(answer, cnt);
			}
			return;
		}
		
		if (cnt >= answer)	return;
		
		// 투여 X
		isSelected[idx] = 0;
		dfs(cnt, idx+1);
		
		// A 투여
		isSelected[idx] = 1;
		dfs(cnt+1, idx+1);
		
		// B 투여
		isSelected[idx] = 2;
		dfs(cnt+1, idx+1);
	}
	
	// 성능 검사
	static boolean check() {
		for (int j=0; j<W; j++) {
			int cur, next;
			int cnt = 1;
			
			for (int i=1; i<D; i++) {
				// cur 설정
				if (isSelected[i-1] > 0) {
					cur = isSelected[i-1] - 1;
				} else {
					cur = map[i-1][j];
				}
				
				// next 설정
				if (isSelected[i] > 0) {
					next = isSelected[i] - 1;
				} else {
					next = map[i][j];
				}
				
				// 비교
				if (cur == next) {
					cnt++;
				} else {
					cur = next;
					cnt = 1;
				}
				
				if (cnt == K)	break;
			}
			if (cnt < K)	return false;
		}
		return true;
	}
}
