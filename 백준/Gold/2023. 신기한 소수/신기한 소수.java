import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N;
	static int[] first = {2, 3, 5, 7};	// 첫번째 자리는 2, 3, 5, 7 만 가능
	static int[] last = {1, 3, 5, 7, 9};	// 이후의 자리는 1, 3, 5, 7, 9 만 가능
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		for (int i=0; i<first.length; i++) {
			dfs(1, first[i]);
		}
		
		System.out.print(sb.toString());
	}

	static void dfs(int depth, int num) {
		if (depth == N) {
			sb.append(num).append("\n");
			return;
		}
		
		for (int i=0; i<last.length; i++) {
			int tmpNum = num*10 + last[i];
			if (isPrime(tmpNum)) {
				dfs(depth+1, tmpNum);				
			}
		}
	}
	
	static boolean isPrime(int n) {
		for (int i=2; i<n; i++) {
			if (n%i == 0) {
				return false;
			}
		}
		return true;
	}
}
