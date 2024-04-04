import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] num, answer;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(num);
		
		answer = new int[M];
		dfs(0);
		
		System.out.println(sb.toString());
	}
	
	static void dfs(int cnt) {
		if (cnt == M) {
			for (int i=0; i<M; i++) {
				sb.append(answer[i]).append(" ");
			} sb.append("\n");
			return;
		}
		
		for (int i=0; i<N; i++) {
			answer[cnt] = num[i];
			dfs(cnt+1);
		}
	}
}
