import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		List<Integer> answer = new ArrayList<>(); 
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for (int i=1; i<=N; i++) {
			q.add(i);
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {			
			if (cnt == K-1) {
				answer.add(q.poll());
				cnt = 0;
			} else {
				q.add(q.poll());
				cnt++;
			}
		}
		
		System.out.println(answer.toString().replace("[", "<").replace("]", ">"));
	}
}

/*7 3
1 2 3 4 5 6 7
3 4 5 6 7 1 2
4 5 6 7 1 2 -> 3
6 7 1 2 4 5
7 1 2 4 5 -> 6
2 4 5 7 1
4 5 7 1 -> 2
7 1 4 5
1 4 5 -> 7
5 1 4
1 4 ->5
1 4
4 -> 1
-> 4
*/