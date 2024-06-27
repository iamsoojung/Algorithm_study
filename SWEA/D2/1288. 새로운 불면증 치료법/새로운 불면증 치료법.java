import java.io.BufferedReader;
import java.io.InputStreamReader;

// N의 배수 번호인 양 / N, 2N, 3N, ... kN (k번째)
public class Solution {

	static int N, cnt, visited, answer = (1 << 10)-1;	// answer : 모든 숫자가 1인지
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			N = Integer.parseInt(br.readLine());
			cnt = 0;	// 양을 몇번 세었는지
			visited = 0;	// 현재까지 본 숫자를 bit로 표현한 수
			
			while(visited != answer) {
				cnt += N;	// N번씩 계속 세기
				char[] ch = String.valueOf(cnt).toCharArray();	// N번 세아린 cnt를 문자열로 저장
				for (char c : ch) {
					int num = c - '0';	// 숫자로
					visited |= (1 << num);
				}
			}
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb.toString());
	}
}
