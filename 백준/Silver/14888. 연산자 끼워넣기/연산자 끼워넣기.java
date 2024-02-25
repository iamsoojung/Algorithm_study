import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;
	static int N;
	static int[] num;
	static int[] op = new int[4];	// +, -, x, %
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		num = new int[N];		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			op[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(num[0], 1);	// 진행된 숫자, 다음 피연산자 인덱스
		
		System.out.println(max);
		System.out.print(min);
		
	}
	
	static void dfs(int n, int idx) {
		if (idx == N) {
			max = Math.max(max, n);
			min = Math.min(min, n);			
			return;
		}
		
		for (int i=0; i<4; i++) {	// 연산자 loop
			if (op[i] > 0) {	// 해당 연산자 존재하면
				
				op[i]--;
				
				switch(i) {
				case 0:
					dfs(n + num[idx], idx+1);
					break;
				case 1:
					dfs(n - num[idx], idx+1);
					break;
				case 2:
					dfs(n * num[idx], idx+1);
					break;
				case 3:
					dfs(n / num[idx], idx+1);
					break;
				}
				
				op[i]++;
			}
		}
	}
}