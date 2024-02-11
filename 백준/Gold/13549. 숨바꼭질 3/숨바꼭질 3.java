import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static Queue<int[]> q = new LinkedList<>();
	public static boolean[] isVisited;
	public static int K;
	public static int time = Integer.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());	// 수빈 위치 
		K = Integer.parseInt(st.nextToken());	// 동생 위치
		
		// 수빈 위치 = X 일때, 1초 후 X-1 or X+1 / 0초 후 2*X
		isVisited = new boolean[100001];	// 방문한 점에는 또 가지 않도록
		bfs(N);
		
		bw.write(time + "");
		bw.close();
		br.close();
	}
	
	public static void bfs(int N) {
		q.offer(new int[]{N, 0});	// 맨 처음 수빈이 위치
		isVisited[N] = true;
		
		while(!q.isEmpty()) {
			int[] lt = q.poll();	// 위치(location), 시간(time)
			
			if (lt[0] == K) {	// 동생 찾았을 때
				time = Math.min(time, lt[1]);
				return;
			}			

			// 0초 후 2*X
			if (0<=(lt[0]*2) && (lt[0]*2)<=100000 && !isVisited[lt[0]*2]) {
				isVisited[lt[0]*2] = true;
				q.offer(new int[]{lt[0]*2, lt[1]});
			}
			
			// 1초 후 X-1
			if (0<=(lt[0]-1) && (lt[0]-1)<=100000 && !isVisited[lt[0]-1]) {
				isVisited[lt[0]-1] = true;
				q.offer(new int[]{lt[0]-1, lt[1]+1});
			}
			
			// 1초 후 X+1
			if (0<=(lt[0]+1) && (lt[0]+1)<=100000 && !isVisited[lt[0]+1]) {
				isVisited[lt[0]+1] = true;
				q.offer(new int[]{lt[0]+1, lt[1]+1});
			}
			
		}
		
	}
}
