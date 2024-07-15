import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
	
	static int N, score, P;
	static int[] list;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());	// 리스트에 있는 점수
		score = Integer.parseInt(st.nextToken());	// 태수의 새로운 점수
		P = Integer.parseInt(st.nextToken());	// 리스트에 올라갈 수 있는 점수의 개수
		
		// 값이 아예 없으면 그냥 -1
		if (N == 0) {
			System.out.println(1);
			return;
		}
		
		list = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		
		// 이미 리스트가 꽉 차있는데, 꼴등보다 태수점수가 낮다면 올라갈 수 없음 => -1
		if (N == P && score <= list[N-1]) {
			System.out.println(-1);
		} else {
			int rank = 1;
			for (int i=0; i<N; i++) {
				if (score >= list[i])	break;	// 등수 찾은 경우
				rank++;
			}
			System.out.println(rank);
		}
	}
}
