import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N, M;
	static int[] num, answer;
	static boolean[] isSelected;
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
		isSelected = new boolean[N];
		dfs(0, 0);
		
		System.out.println(sb.toString());
	}
	static void dfs(int cnt, int start) {
		if (cnt == M) {
			for (int i=0; i<M; i++) {
				sb.append(answer[i]).append(" ");
			}sb.append("\n");
			return;
		}
		
		int before = 0;
		for (int i=start; i<N; i++) {
			if (before != num[i]) {
				answer[cnt] = num[i];
				before = num[i];
				dfs(cnt+1, i+1);
			}
		}
	}
}
