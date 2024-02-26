import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	
	static int N, X, K;
	static int[] arr;
	static int result;
 
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = 0;
			arr = new int[N+1];
			arr[X] = 1;
			
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				int tmp = arr[A];
				arr[A] = arr[B];
				arr[B] = tmp;
			}
			
			for(int i=0; i<=N; i++) {
				if(arr[i] == 1) {
					result = i;
				}
			}
			bw.write(result + "\n");
		
		
		bw.close();
		
	}
	
}
