import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String s = br.readLine();
		
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int answer = 0;
		
		for (int i=0; i<N; i++) {
			switch(s.charAt(i)) {
			case 's':
				stack.push(1);
				break;
			case 'k':
				if (!stack.isEmpty() && stack.peek() == 1) {
					stack.push(stack.pop() + 1);
				} else {
					stack.clear();
				}
				break;
			case 'e':
				if (!stack.isEmpty() && (stack.peek() == 2 || stack.peek() == 3)) {
					stack.push(stack.pop() + 1);
				} else {
					stack.clear();
				}
				break;
			case 'p':
				if (!stack.isEmpty() && stack.peek() == 4) {
					stack.push(stack.pop() + 1);
				} else {
					stack.clear();
				}
				break;
			default:
				stack.clear();
				break;
			}
			
			while (!stack.isEmpty() && stack.peek() == 5) {
				stack.pop();
				answer++;
				if (!stack.isEmpty()) {
					stack.push(stack.pop() + 1);
				}
			}
		}
		System.out.print(answer);		
	}
}