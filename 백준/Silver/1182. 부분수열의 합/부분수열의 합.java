import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, S, answer=0;
	static int[] num;
	static boolean[] isSelected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());	// 정수의 개수
		S = Integer.parseInt(st.nextToken());	// 정수
		
		num = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i=0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		subset(0, 0, 0);
		
		System.out.print(answer);
	}

	static void subset(int idx, int sum, int cnt) {

		if (idx == N) {
			if (cnt != 0 && sum == S) {
				answer++;
			}
			return;
		}

		subset(idx+1, sum+num[idx], cnt+1);
		subset(idx+1, sum, cnt);
	}
}
