import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	
	static StringTokenizer st;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[][] matter = new int[N][2];
		
		for (int i=0; i<N; i++) {			
			st = new StringTokenizer(br.readLine());
			matter[i][0] = Integer.parseInt(st.nextToken());
			matter[i][1] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(matter, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		int low = matter[0][0];
		int high = matter[0][1];
		boolean flag;
		int cnt = 1;
		
		for (int i=1; i<N; i++) {
			flag = false;
			if (low <= matter[i][0] && matter[i][0] <= high) {
				low = matter[i][0];
				flag = true;
			}
			if (low <= matter[i][1] && matter[i][1] <= high) {
				high = matter[i][1];
				flag = true;
			}
			if (!flag) {
				cnt += 1;
				low = matter[i][0];
				high = matter[i][1];
			}
		}
		System.out.print(cnt);
	}
}