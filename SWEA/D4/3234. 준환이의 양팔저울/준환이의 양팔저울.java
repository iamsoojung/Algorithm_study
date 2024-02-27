import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {	
	static int N, cnt;
	static int[] w;	
	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {
			
			N = Integer.parseInt(br.readLine());	// 무게추 갯수
			w = new int[N];
			
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i=0; i<w.length; i++) {
				w[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(w);		// 정렬
			cnt = 0;	// 가지 수 카운팅
			do {
				go(0, 0, 0);
			} while (np());			
			sb.append("#").append(testCase).append(" ").append(cnt).append("\n");
		}
		System.out.print(sb);
	}
	
	public static void go(int step, int l, int r) {
		if (step == N) {
			cnt++;
			return;
		}
		go(step+1, l+w[step], r);	// 왼쪽
		if (l >= r+w[step]) {
			go(step+1, l, r+w[step]);	// 오른쪽 (왼쪽보다 커지면 안된다)
		}
	}
	
	// 사전순 순열의 다음번 순열을 만들어 주는 메서드 (next permutation)
	// 다음번 사전 순 순열이 있으면 true 리턴, 없으면 false 리턴
	public static boolean np() {
		// 순열 끝부터 탐색 => 증가하는 부분 찾기
		int i = N-1;
		while (0 < i && w[i-1] > w[i]) {
			i--;	// 꼭대기 찾기 i, i-1번째 값을 바꾸기
		}
		
		// 만약 증가하는 부분이 존재하지 않는다면, 다음 순열은 존재 X
		if (i == 0)	return false;	// 순열의 끝

		// 해당 인덱스 기준으로 좌/우 지점 나누기
		// 좌측의 제일 오른쪽 숫자에 대해, 우측의 제일 오른쪽 지점부터 탐색하며 큰 수 찾기
		int j = N-1;
		while(w[i-1] >= w[j]) {
			j--;
		}
		swap(i-1, j);
		
		int k = N-1;
		while (i < k) {
			swap(i++, k--);	// 꼭대기 ~ 끝 재정렬 (내림차순 -> 오름차순)
		}
		return true;
		// memo
		// j 맨뒤부터 찾아서 i-1보다 큰 것 찾기, j <-> i-1
		// 꼭대기~끝 재정렬 (내림차순 -> 오름차순)
	}
	
	public static void swap(int i, int j) {
		int tmp = w[i];
		w[i] = w[j];
		w[j] = tmp;
	}
}