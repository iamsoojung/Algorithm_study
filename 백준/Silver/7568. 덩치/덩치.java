import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int[][] spec = new int[N][2];
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			spec[i][0] = Integer.parseInt(st.nextToken());
			spec[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i=0; i<N; i++) {
			int rank = 1;
			for (int j=0; j<N; j++) {
				if (i==j)	continue;
				if (spec[i][0] < spec[j][0] && spec[i][1] < spec[j][1]) {
					rank++;
				}
			}
			sb.append(rank).append(" ");
		}
		System.out.println(sb.toString());
	}
}