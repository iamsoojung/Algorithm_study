import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());	// 과일의 개수
		int L = Integer.parseInt(st.nextToken());	// 스네이크버드 초기 길이
		
		st = new StringTokenizer(br.readLine());
		int[] fruits = new int[N];
		for (int i=0; i<N; i++) {
			fruits[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(fruits);
		
		for (int f : fruits) {
			if (f > L)	break;
			L++;
		}
		
		System.out.print(L);
	}
}
