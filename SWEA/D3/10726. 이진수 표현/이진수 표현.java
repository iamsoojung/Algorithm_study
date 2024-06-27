import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			int lastNBit = (1<<N) - 1;	// 마지막 N 비트 모두 1인 수 (N 범위가 30 미만이므로 int)
			if (lastNBit == (lastNBit & M)) {
				sb.append("#").append(testCase).append(" ON\n");
			} else {
				sb.append("#").append(testCase).append(" OFF\n");
			}
		}
		System.out.println(sb.toString());
	}
}
