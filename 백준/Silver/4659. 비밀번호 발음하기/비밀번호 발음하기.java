import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true) {
			String s = br.readLine();
			if (s.equals("end"))	break;
			
			char[] arr = s.toCharArray();
			
			boolean containsVowels = false;	// 1. 모음 포함?
			boolean tripleConsonant = false;	// 2. 모음, 자음 3번 연속?
			boolean consecutiveLetters = false;	// 3. 글자 연속?
			int vowels = 0;		// 모음 횟수
			int consonants = 0;	// 자음 횟수
			
			for (int i=0; i<arr.length; i++) {
				// 모음(aeiou) 하나를 반드시 포함
				if (arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
					containsVowels = true;
					vowels++;
					consonants = 0;
				} else {
					consonants++;
					vowels = 0;
				}
				
				// 모듬 3개 or 자음 3개 연속 금지
				if (vowels >= 3 || consonants >= 3) {
					tripleConsonant = true;
					break;
				}
				
				// 같은 글자 연속 금지 (e, o는 허용)
				if (0 < i && arr[i-1] == arr[i]) {
					if (arr[i] == 'e' || arr[i] == 'o')	continue;
					consecutiveLetters = true;
					break;
				}
			}
			
			if (containsVowels && !tripleConsonant && !consecutiveLetters) {
				sb.append("<").append(s).append("> is acceptable.\n");
			} else {
				sb.append("<").append(s).append("> is not acceptable.\n");				
			}
		}
		System.out.println(sb.toString());
	}
}