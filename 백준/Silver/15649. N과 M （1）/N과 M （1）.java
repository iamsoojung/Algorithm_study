import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열
public class Main {
	
	static int N, M;
	static int[] numbers;
	static boolean[] isSelected;
	public static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		numbers = new int[M];
		
		isSelected = new boolean[N+1];	// 숫자 사용했는지 관리 (1~N)
		perm(0);
		System.out.print(sb);
	}
	
	public static void perm(int depth) throws IOException {
		
		if (depth == M) {
			for (int n : numbers) {
				sb.append(n).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		for (int i=1; i<=N; i++) {
			if (isSelected[i])	continue;	// 숫자 선택 시
			numbers[depth] = i;
			isSelected[i] = true;
			perm(depth + 1);
			isSelected[i] = false;
		}
		return;
	}

}
