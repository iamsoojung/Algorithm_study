import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int[] cur;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		cur = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			cur[i] = Integer.parseInt(st.nextToken());
		}
		
		NP(cur);
		System.out.println(sb);
	}
	
	static void NP(int[] cur) {
		int i = N-1;
		while (i>0 && cur[i-1] >= cur[i])	--i;
		
		// 사전순으로 마지막에 오는 순열인 경우 -1 출력
		if (i == 0) {
			sb.append(-1 + "");
			return;
		}
		
		int j = N-1;
		while (cur[i-1] >= cur[j])	--j;
		
		swap(cur, i-1, j);
		
		int k = N-1;
		while(i < k)	
		
		while(i<k)	swap(cur, i++, k--);
		
		for (int c=0; c<N; c++) {
			sb.append(cur[c]+ "");
			if (c != N-1) {
				sb.append(" ");
			}
		}
		return;
	}
	
	static void swap(int[] cur, int i, int j) {
		int tmp = cur[i];
		cur[i] = cur[j];
		cur[j] = tmp;
	}

}
