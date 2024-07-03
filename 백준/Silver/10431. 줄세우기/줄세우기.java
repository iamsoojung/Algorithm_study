import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] arr = new int[20];
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int P = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=P; testCase++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			
			for (int i=0; i<20; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			int cnt = 0;
			for (int i=19; i>=0; i--) {
				for (int j=i-1; j>=0; j--) {
//					System.out.println(arr[j] + " " + arr[i]);
					if (arr[j] > arr[i]) {
						int tmp = arr[i];
						arr[i] = arr[j];
						arr[j] = tmp;
						cnt++;
					}
				} 
			}
			
			sb.append(testCase).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb.toString());
	}
}