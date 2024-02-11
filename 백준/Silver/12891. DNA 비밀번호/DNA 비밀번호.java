import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static StringTokenizer st;
	static int S, P, result;
	static char[] DNA;
	static int[] includeChar = new int[4];	//  {‘A’, ‘C’, ‘G’, ‘T’} 의 최소 개수
	static int[] tmpChar = new int[4];	// 부분 문자열마다 개수 구하기 위한 임시 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());	// 임의로 만든 DNA 문자열의 길이
		P = Integer.parseInt(st.nextToken());	// 비밀번호 사용할 부분문자열의 길이
		
		DNA = br.readLine().toCharArray();	// 임의로 만든 DNA 문자열
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<4; i++) {
			includeChar[i] = Integer.parseInt(st.nextToken());
		}
		
		// 첫 부분 문자열 세팅
		for (int i=0; i<P; i++) {
			tmpChar[checkIdx(DNA[i])]++;
		}
		result = checkPassword() ? 1 : 0;
		
		for (int i=1; i<=S-P; i++) {	// 시작 : i, 종료 : i+P
			tmpChar[checkIdx(DNA[i-1])]--;
			tmpChar[checkIdx(DNA[i+P-1])]++;
			if (checkPassword()) {
				result++;
			}
		}
		System.out.println(result);
	}
	static int checkIdx(char ch) {
		switch(ch) {
		case 'A':	return 0;
		case 'C':	return 1;
		case 'G':	return 2;
		case 'T':	return 3;			
		}
		return -1;
	}
	static boolean checkPassword() {
		for (int i=0; i<4; i++) {
			if (tmpChar[i] < includeChar[i]) {
				return false;
			}
		}
		return true;
	}
}
