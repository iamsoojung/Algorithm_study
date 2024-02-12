import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		// StringTokenizer st = new StringTokenizer(br.readLine());
		// StringTokenizer을 역순 저장
		StringTokenizer st = new StringTokenizer(new StringBuilder(br.readLine()).reverse().toString());
		
		Deque<Integer> card = new ArrayDeque<>();
		for (int i=1; i<=N; i++) {
			int technic = Integer.parseInt(st.nextToken());
			
			if (technic==1) {		// 제일 위의 카드 => 맨 앞 추가
				card.addFirst(i);
			}else if (technic==2) {	// 위에서 두 번째 카드 => 두번째 추가 (맨앞 빼고 이거넣고 뺀거다시넣기)
				int tmp = card.removeFirst();
				card.addFirst(i);
				card.addFirst(tmp);
			}else if (technic==3) {	// 제일 밑에 있는 카드 => 맨 뒤 추가
				card.addLast(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		while (card.size() != 0) {
			sb.append(card.removeFirst()).append(" ");
		}
		System.out.println(sb);
	}
}
