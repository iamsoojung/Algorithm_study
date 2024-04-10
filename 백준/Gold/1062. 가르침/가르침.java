import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K, maxSize=0, answer;	// 읽을 수 있는 단어의 최댓값
	static boolean[][] word;
	static char[] teachAlpha;
	static boolean[] select = new boolean[26];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 남극언어에 존재하는 단어 수
		K = Integer.parseInt(st.nextToken());	// 가르칠 글자 수
		
		word = new boolean[N][26];
		for (int i=0; i<N; i++) {
			String s = br.readLine();
			for (int j=0; j<s.length(); j++) {
				word[i][s.charAt(j) - 'a'] = true;
			}
			maxSize = Math.max(maxSize, s.length());
		}
		
		if (maxSize < 5) {
			System.out.println(0);
		} else {
			// 가르치는 경우의 수
			teachAlpha = new char[K];
			
			// 'anta' 로 시작, 'tica'로 끝
			select['a' - 'a'] = true;
			select['n' - 'a'] = true;
			select['t' - 'a'] = true;
			select['i' - 'a'] = true;
			select['c' - 'a'] = true;
			
			comb(0, 5);
			
			System.out.println(answer);
		}
	}
	
	static void comb(int start, int cnt) {
		if (cnt == K) {
			answer = Math.max(answer, check());
			return;
		}
		
		for (int i=start; i<26; i++) {
			if (!select[i]) {
				select[i] = true;
				comb(i+1, cnt+1);
				select[i] = false;
			}
		}
	}
	
	static int check() {
		int cnt = 0;
		for (int i=0; i<N; i++) {
			boolean flag = false;
			for (int j=0; j<26; j++) {
				if (word[i][j] && !select[j]) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				cnt++; 
			}
		}
		
		return cnt;
	}
}