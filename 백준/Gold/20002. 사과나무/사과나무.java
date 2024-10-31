import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N*N 과수원(각 칸에 사과나무) 내 K*K 모양으로 수확 가능 (1 <= K <= N)
 * 나무 1그루 수확할 때마다, 사과 통해 얻는 이익 & 노동비 동시에 이루어짐
 * 나무 위치 좌표로 하여, 총이익 (얻는 이익 + 노동비)을 2차원 배열 형태로 정리함
 * 최대 총이익?
 */
// 1트 : getCost 메서드에서 O(N^4)가 되어서 시간초과 생김
// 2트 : 2차원 구간합 배열로 O(N^2)로 고침

public class Main {
	
	static int N, answer;
	static int[][] map, prefixSum;
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		prefixSum = new int[N+1][N+1];	// (1,1) ~ (N, N) 합 계산
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// prefix sum array 계산
		// prefix sum의 (i,j) == map의 (i-1, j-1)까지의 합
		for (int i=1; i<=N; i++) {
			for (int j=1; j<=N; j++) {
				prefixSum[i][j] = map[i-1][j-1] 
								+ prefixSum[i-1][j] + prefixSum[i][j-1]
								- prefixSum[i-1][j-1];
			}
		}
		
		answer = Integer.MIN_VALUE;
		for (int k=1; k<=N; k++) {	// 1~N 만큼 K 시도
			answer = Math.max(answer, getCost(k));
		}
		
		System.out.println(answer == 0 ? -1 : answer);
	}
	
	static int getCost(int K) {
		
		int result = Integer.MIN_VALUE;
		for (int i=K; i<=N; i++) {
			for (int j=K; j<=N; j++) {
				int sum = prefixSum[i][j]
						- prefixSum[i-K][j] - prefixSum[i][j-K]
						+ prefixSum[i-K][j-K];
				result = Math.max(result, sum);
			}
		}
		return result;
	}
}