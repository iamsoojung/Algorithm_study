import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합
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
		
		comb(0, 1);
		System.out.print(sb);
	}
	
	public static void comb(int depth, int start){
		
		if (depth == M) {
			for (int n : numbers) {
				sb.append(n).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i=start; i<=N; i++) {
			numbers[depth] = i;
			comb(depth + 1, i+1);
		}
		return;
	}

}
