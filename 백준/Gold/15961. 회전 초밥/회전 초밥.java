import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int N, d, k, c, tmp=0, answer=0;
	static int[] sushi;		// 놓인 초밥 순서
	static int[] visit;		// 포함된 종류 인지
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 벨트에 놓인 접시의 수 N
		d = Integer.parseInt(st.nextToken());	// 초밥의 가짓 수 d
		k = Integer.parseInt(st.nextToken());	// 연속해서 먹는 접시의 수 k
		c = Integer.parseInt(st.nextToken());	// 쿠폰 번호 c
		
		sushi = new int[N+k-1];	// 처음과 끝이 이어져 있으므로, k-1 만큼 뒤에 더 붙여버릴거임
		visit = new int[d+1];
		
		for (int i=0; i<N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}
		for (int i=0; i<k-1; i++) {
			sushi[N+i] = sushi[i];
		}
		
		// 놓여있는 초밥 수 만큼 loop
		// k 만큼 한 묶음으로 보며 점점 한칸씩 이동 (앞에꺼 -, 뒤에꺼 +)
			// 한 묶음에서 종류 수 계산
			// 별도 리스트에 계산 결과 저장해놓기
		// 별도 리스트에서 제일 큰 값 출력하자
		// ** 슬라이딩 윈도우 ** 인듯? DNA 비밀번호와 유사한 문제

		// 초기 세팅
		for (int i=0; i<k; i++) {
			visit[sushi[i]]++;
		}
		
		for (int i=1; i<=d; i++) {
			if (visit[i] > 0)	tmp++;
		}
		
		if (visit[c] == 0)	tmp++;

		// 슬라이딩 윈도우 진행		
		for (int i=0; i<N-1; i++) {
			// 앞에꺼 -
			visit[sushi[i]]--;
			if (visit[sushi[i]] == 0) {
				if (sushi[i] != c)	tmp--;
			}
			
			// 뒤에꺼 +
			visit[sushi[i+k]]++;
			if (visit[sushi[i+k]] == 1) {
				if (sushi[i+k] != c)	tmp++;
			}
			
			if (answer < tmp) {
				answer = tmp;
			}
		}

		System.out.print(answer);
	}
}