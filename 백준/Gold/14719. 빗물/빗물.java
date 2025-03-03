import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		int[] block = new int[W];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<W; i++) {
			block[i] = Integer.parseInt(st.nextToken());
		}
			
		int answer = 0;
		for (int i=1; i<W-1; i++) {
			int left=0, right=0;
			
			// 왼쪽 젤 높은 블록 찾기
			for (int j=0; j<i; j++) {
				left = Math.max(left, block[j]);
			}
			
			// 오른쪽 젤 높은 블록 찾기
			for (int j=i+1; j<W; j++) {
				right = Math.max(right, block[j]);
			}
			
			if (left > block[i] && block[i] < right) {
				// 고인 빗물 계산
				int wall = Math.min(left, right);
				answer += wall - block[i];
			}
		}
		
		System.out.print(answer);	
	}
}
