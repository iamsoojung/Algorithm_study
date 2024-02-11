import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 웹페이지의 종류의 수
		int Q = Integer.parseInt(st.nextToken());	// 사용자가 수행하는 작업의 개수
		
		Stack<Integer> forward = new Stack<Integer>();		// 앞으로 가기 공간
		Stack<Integer> backward = new Stack<Integer>();	// 뒤로 가기 공간
		int cnt = 0;	// 처음 접속 여부
		
		int page = 0;
		for (int i=0; i<Q; i++) {	// Q개의 작업
			st = new StringTokenizer(br.readLine());
			char work = st.nextToken().charAt(0);
			
			switch(work) {
			case 'B':
				if (backward.size() != 0) {
					forward.push(page);
					page = backward.pop();
				}
				break;
			case 'F':
				if (forward.size() != 0) {
					backward.push(page);
					page = forward.pop();
				}
				break;
			case 'A':
				if (cnt == 0) {
					cnt++;
					page = Integer.parseInt(st.nextToken());
					break;
				}
				backward.push(page);
				page = Integer.parseInt(st.nextToken());	// 접속한 페이지
				forward.clear();
				break;
			case 'C':
				Stack<Integer> stack = new Stack<>();
				int cur = 0;	// 가장 최근의 페이지
				while(!backward.empty()) {
					int tmp = backward.pop();
					if (tmp != cur) {
						stack.push(tmp);
						cur = tmp;
					}
				}
				while (!stack.empty()) {
					backward.push(stack.pop());
				}
				break;
			}
		}
		sb.append(page + "\n");
		
		if (backward.empty()) {
			sb.append(-1 + "\n");
		} else {
			while (!backward.empty()) {
				sb.append(backward.pop() + " ");
			}
			sb.append("\n");
		}
		
		if (forward.empty()) {
			sb.append(-1 + "\n");
		} else {
			while (!forward.empty()) {
				sb.append(forward.pop() + " ");
			}
		}
			
		bw.write(sb.toString());
		bw.close();
		br.close();
	}
}
