import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int answer = 26;	// 최대 25라서
	static int[][] map = new int[10][10];	// 색종이를 붙일 필드
	static int[] paper = {5, 5, 5, 5, 5};	// 1×1, 2×2, 3×3, 4×4, 5×5로 총 다섯 종류
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		for (int i=0; i<10; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<10; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		System.out.print(answer == 26 ? -1 : answer);
	}
	
	static boolean inRange(int i, int j, int k) {
		return i+k<10 && j+k<10;
	}
	
	static boolean check(int i, int j, int k) {
		for (int a=i; a<=i+k; a++) {
			for (int b=j; b<=j+k; b++) {
				if (map[a][b] == 0)	{
					return false;
				}
			}
		}
		return true;
	}
	
	static boolean fillCheck() {
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (map[i][j] == 1) {
					return false;
				}
			}
		}
		return true;
	}
	
	static void fill(int i, int j, int k, int color) {
		for (int a=i; a<=i+k; a++) {
			for (int b=j; b<=j+k; b++) {
				map[a][b] = color;
			}
		}
	}
	
	static void dfs(int cnt) {
		if (answer < cnt)	return;	// 어차피 정답보다 클텐데 봐서 뭐하냐
		
		// 모두 0인지 체크
		if (fillCheck()) {
			answer = Math.min(answer, cnt);
			return;
		}
		
		for (int i=0; i<10; i++) {
			for (int j=0; j<10; j++) {
				if (map[i][j] == 1) {	// 1이라면 색종이 붙이기
					// 5종류 다 대보기
					for (int k=4; k>=0; k--) {
						// 붙일 색종이 남아 있음 & inRange & 붙일 곳에 0 없음
						if (paper[k]>0 && inRange(i, j, k) && check(i, j, k)) {

							// 붙이기
							fill(i, j, k, 0);
							paper[k]--;
							
							dfs(cnt+1);
							
							// 떼기 (원복)
							fill(i, j, k, 1);
							paper[k]++;
						}
					}
					return;	// 재귀 돌린 depth에서 또 1인 곳 찾으면 됨, 이건 필요 X
				}
			}
		}
	}
}
