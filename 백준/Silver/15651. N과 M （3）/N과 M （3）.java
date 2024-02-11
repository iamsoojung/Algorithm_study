import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중복 순열
public class Main {
	
	static int N, M;
	static int[] numbers;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		
		perm_with_rep(0);
		System.out.print(sb);
	}
	
	public static void perm_with_rep(int depth){
		
		if (depth == M) {
			for (int n : numbers) {
				sb.append(n).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i=1; i<=N; i++) {
			numbers[depth] = i;
			perm_with_rep(depth + 1);
		}
		return;
	}

}
