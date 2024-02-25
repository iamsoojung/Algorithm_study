import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {	
	
	static int N;	// 길이가 N인 수식
	static ArrayList<Integer> num;	// 피연산자
	static ArrayList<Character> op;	// 연산자
	static int answer = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		num = new ArrayList<>();
		op = new ArrayList<>();
		
		String s = br.readLine();
		for (int i=0; i<N; i++) {
			char c = s.charAt(i);
			if (i%2 == 0) {
				num.add(c-'0');
			} else {
				op.add(c);
			}
		}
		
		int start = num.get(0);
		dfs(start, 0);	// 0번째 숫자, 0번째 연산자
		
		System.out.print(answer);
	}
	
	static void dfs(int sum, int idx) {
		if (idx >= op.size()) {
			answer = Math.max(answer, sum);
			return;
		}
		
		// 괄호 O
		int newSum = calc(idx, sum, num.get(idx+1));
		dfs(newSum, idx+1);
		
		if (idx+1 < op.size()) {	// 인덱스가 연산자 갯수 초과하지 않는 경우에만
			// 괄호 X
			int tmp1 = calc(idx+1, num.get(idx+1), num.get(idx+2));
			newSum = calc(idx, sum, tmp1);
			dfs(newSum, idx+2);
		}
	}
	
	static int calc(int opIdx, int a, int b) {
		switch(op.get(opIdx)) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		}
		return 0;
	}
}