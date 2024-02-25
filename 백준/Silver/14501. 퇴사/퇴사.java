import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {	
	static int N;
	static int[][] consult;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		consult = new int[N][2];
		
		for (int i=0; i<N; i++) {	// 걸리는 시간 T, 받는 금액 P
			st = new StringTokenizer(br.readLine());
			consult[i][0] = Integer.parseInt(st.nextToken());
			consult[i][1] = Integer.parseInt(st.nextToken());
		}
		
		result = 0;
		dfs(0, 0);
		System.out.print(result);
	}
	
	static void dfs(int idx, int total) {	// 상담 소요일, 총 금액
		if (idx >= N) {
			result = Math.max(result, total);
			return;
		}
		
		int T = consult[idx][0];
		int P = consult[idx][1];
		
		if (idx + T <= N) {	// 상담 끝 가능
			dfs(idx+T, total+P);
		} else {	// 상담이 안끝나요..
			dfs(idx+T, total);
		}
		dfs(idx+1, total);
	}
	
	
}