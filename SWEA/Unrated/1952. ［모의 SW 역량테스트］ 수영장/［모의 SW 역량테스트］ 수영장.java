import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//가장 적은 비용으로 수영장 이용
//이용권 4종류 -> 1일, 1달, 3달, 1년
public class Solution {
	
	static int answer;
	static int[] fee, plan;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			fee = new int[4];		// 이용권 가격들
			plan = new int[13];		// 12개월 이용 계획
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				fee[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i=1; i<=12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}
			
			answer = fee[3];
			dfs(1, 0);
			
			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void dfs(int month, int cost) {
		if (answer < cost)	return;	// 이미 answer 넘음
		
		if (month > 12) {
			answer = Math.min(answer, cost);
			return;
		}
		
		if (plan[month] == 0) {
			dfs(month+1, cost);
		} else {
			dfs(month+1, cost + (plan[month]*fee[0]));	// 1일
			dfs(month+1, cost + fee[1]);	// 1달
			dfs(month+3, cost + fee[2]);	// 3달
		}
	}
}