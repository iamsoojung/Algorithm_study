import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	
	static class Info {
		int A, T;

		public Info(int a, int t) {
			super();
			A = a;
			T = t;
		}
	}
	
	static int N, answer = 0;
	static int flag;
	static Stack<Info> s = new Stack<>();
	static Info[] list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());	// 몇분 ?
		
		int curA, curT;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			flag = Integer.parseInt(st.nextToken());
			
			if (flag == 1) {
				
				curA = Integer.parseInt(st.nextToken());
				curT = Integer.parseInt(st.nextToken())-1;
				
				if (curT == 0) {
					answer += curA;
					continue;
				}				
				s.push(new Info(curA, curT));
				
			} else {
				if (!s.isEmpty()) {
					Info tmp = s.pop();
					tmp.T--;
					
					if (tmp.T == 0) {
						answer += tmp.A;
					} else {
						s.push(tmp);
					}
				}
			}
		}
		System.out.print(answer);
	}
}
