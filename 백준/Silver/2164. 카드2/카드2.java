import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Queue<Integer> q = new ArrayDeque<>();
		
		// N 크기만큼 카드 초기화
		for (int i=1; i<=N; i++) {
			q.add(i);
		}
		
		while (q.size() > 1) {	// 큐에 한장의 카드만 남을 때까지
			q.poll();	// 맨 위 카드 버리기
			q.add(q.poll());	// 맨 위 카드를 맨 아래로 옮기기
		}
		
		System.out.println(q.poll());	// 제일 마지막에 남는 카드
		br.close();
	}

}
