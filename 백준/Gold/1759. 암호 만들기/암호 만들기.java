import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static StringBuilder sb;
	static int L, C;
	static char[] alpha;
	static char[] answer;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		L = Integer.parseInt(st.nextToken());	// 만들 암호의 길이
		C = Integer.parseInt(st.nextToken());	// 알파벳 개수
		alpha = new char[C];
		answer = new char[L];
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpha);
		makeCode(0, 0);
		System.out.print(sb);
	}
	
	static void makeCode(int depth, int start) {
		if (depth == L) {
			if (isValid()) {
				sb.append(answer).append("\n");
			}
			return;
		}
		
		for (int i=start; i<C; i++) {
			answer[depth] = alpha[i];
			makeCode(depth+1, i+1);
		}
	}
	
	static boolean isValid() {
		int mCnt=0, jCnt=0;		// 최소 1개의 모음, 최소 2개의 자음
		for (int i=0; i<L; i++) {
			if (answer[i] == 'a' || answer[i] == 'e' || answer[i] == 'i' || answer[i] == 'o' || answer[i] == 'u') {
				mCnt++;
			} else {
				jCnt++;
			}
		}
		
		if (mCnt >= 1 && jCnt >= 2) {
			return true;
		} else {
			return false;
		}
	}
}
