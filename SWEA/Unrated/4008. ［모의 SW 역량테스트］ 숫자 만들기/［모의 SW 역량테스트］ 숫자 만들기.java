import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int N, max, min;
	static int[] op;	// 각 연산자의 개수
	static int[] num;	// 수식에 사용되는 숫자
	static int[] opArr;	// 연산 순서 저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case=1; test_case<=T; test_case++) {
			N = Integer.parseInt(br.readLine());
			op = new int[4];
			num = new int[N];
			opArr = new int[N-1];
			
			st = new StringTokenizer(br.readLine());
			for (int i=0; i<4; i++) {
				op[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<N; i++) {
				num[i] = Integer.parseInt(st.nextToken());
			}
			
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			operator(0);
			
			sb.append("#" + test_case + " " + (max-min) + "\n");
		}
		System.out.print(sb);
		br.close();
	}
	
	static void operator(int cnt) {
		if (cnt == N-1) {
			calculator(opArr);
			return;
		}
		
		for (int i=0; i<4; i++) {
			if (op[i] == 0)	continue;
			op[i]--;
			opArr[cnt] = i;
			operator(cnt+1);
			op[i]++;
		}
	}
	
	static void calculator(int[] opArr) {
		int curN = num[0];	// 현재 숫자
		for (int i=0; i<N-1; i++) {
			switch(opArr[i]) {
			case 0:
				curN += num[i+1];
				break;
			case 1:
				curN -= num[i+1];
				break;
			case 2:
				curN *= num[i+1];
				break;
			case 3:
				curN /= num[i+1];
				break;
			}
		}
		if (max < curN) {
			max = curN;
		}
		if (min > curN) {
			min = curN;
		}
	}
}