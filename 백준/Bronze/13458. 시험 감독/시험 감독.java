import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N;		// 시험장의 개수 N
	static int[] A;		// 각 시험장에 있는 응시자 수
	static int B, C;	// 총감독관 감시가능 응시자 수, 부감독관 감시가능 응시자 수
	static long answer = 0;		// 가능한 시험장 개수와 학생 수가 매우 큼
	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		for (int i=0; i<N; i++) {
			A[i] -= B;
			answer++;
			
			if (A[i] <= 0)	continue;
			
			answer += (A[i]/C);
			if (A[i]%C != 0)	answer++;
		}
		
		System.out.println(answer);
		br.close();
	}
}