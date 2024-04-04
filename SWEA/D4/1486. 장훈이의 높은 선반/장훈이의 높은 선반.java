import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, B, answer;
	static int[] H;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 점원 수
			B = Integer.parseInt(st.nextToken());	// 선반 높이
			
			H = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				H[i] = Integer.parseInt(st.nextToken());
			}
			
			isSelected = new boolean[N];
			answer = Integer.MAX_VALUE;
			subset(0, 0);
			
			sb.append("#").append(testCase).append(" ").append(Math.abs(answer-B)).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void subset(int idx, int sum) {
		if (idx == N) {
			if (sum >= B) {		// 선반보다 같거나 높다면 충족
				answer = Math.min(answer, sum);		// 최솟값 갱신
			}
			return;
		}
		
		isSelected[idx] = true;
		subset(idx+1, sum + H[idx]);
		
		isSelected[idx] = false;
		subset(idx+1, sum);
	}
}
