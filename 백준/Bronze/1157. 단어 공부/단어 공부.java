import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String s = br.readLine();
		int[] alpha = new int[26];
		
		for (int i=0; i<s.length(); i++) {
			int target = s.charAt(i);

			if ('A' <= target && target <= 'Z') {	// 대문자
				alpha[target-'A']++;
			} else {	// 소문자
				alpha[target-'a']++;
			}
		}
		
		int max = -1;
		char answer = '?';
		
		for (int i=0; i<26; i++) {
			if (alpha[i] > max) {
				max = alpha[i];
				answer = (char) (i + 'A');
			} else if (alpha[i] == max) {
				answer = '?';
			}
		}
		
		System.out.println(answer);
	}
}