import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Stack<int []> stack = new Stack<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 1 2 3 4 5 (idx)
		// 6 9 5 7 4 (top[i])
		// 0 0 2 2 4 (num)
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			int target = Integer.parseInt(st.nextToken());
			
			while (!stack.empty()) {
				if (stack.peek()[1] >= target) {
					sb.append(stack.peek()[0] + " ");
					break;
				}
				stack.pop();
			}
		
			if (stack.empty()) {
				sb.append(0 + " ");
			}
			
			stack.push(new int[] {i+1, target});
		}
		
		System.out.print(sb);
		br.close();
		
	}

}
