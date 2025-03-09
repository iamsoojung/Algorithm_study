import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, answer = Integer.MIN_VALUE;	// 최대 인원
	static int[][] meetings;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		meetings = new int[N][3];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i][0] = Integer.parseInt(st.nextToken());
			meetings[i][1] = Integer.parseInt(st.nextToken());
			meetings[i][2] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0, 0);
		System.out.print(answer);
	}
	
	static void dfs(int sum, int curStart) {
		if (answer < sum) {
			answer = sum;
		}
		
		for (int i=0; i<N; i++) {
			if (curStart <= meetings[i][0]) {
				dfs(sum + meetings[i][2], meetings[i][1]);
			}
		}
	}
}