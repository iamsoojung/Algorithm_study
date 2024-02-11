import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	public static int k;	// 고를 수 갯수
	public static int S[];		// 후보 수 집합
	public static int numbers[];	// 뽑은 수 집합
	public static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		while (true) {
			sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			
			if (k == 0) break;
			
			S = new int[k];
			for (int i=0; i<k; i++) {
				int n = Integer.parseInt(st.nextToken());
				S[i] = n;
			}

			numbers = new int[6];
			pick(0, 0);
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
	}
	
	public static void pick(int depth, int start) {
		
		if (depth == 6) {
			for (int i=0; i<6; i++) {
				sb.append(numbers[i]);
				if (i != 5)	sb.append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i=start; i<S.length; i++) {
			numbers[depth] = S[i];
			pick(depth+1, i+1);
		}
		return;
	}
	
}
