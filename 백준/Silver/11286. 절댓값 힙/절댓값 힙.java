import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				// 절대값이 크다면 자리 바꾸기
				if (Math.abs(o1) > Math.abs(o2)) {
					return Math.abs(o1) - Math.abs(o2);
				} 
				// 절대값이 같다면 
				else if (Math.abs(o1) == Math.abs(o2)) {
					return o1-o2;	// 오름차순
				}
				else {
					return -1;	// left가 왼쪽에 옴
				}
			}
		});
		
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<N; i++) {
			int x = Integer.parseInt(br.readLine());
			if (x != 0) {	// 배열에 x 값 추가
				pq.offer(x);
			} else {	// 배열에서 절댓값이 가장 작은 값 출력 후 제거
				if (pq.isEmpty()) {
					sb.append("0").append("\n");
				} else {
					sb.append(pq.poll()).append("\n");
				}
			}	
		}
		System.out.println(sb);
		br.close();
	}
}
