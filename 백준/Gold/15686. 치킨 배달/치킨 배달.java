import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
	
	static class Point {
		int x;
		int y;
		
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N, M, input, answer=Integer.MAX_VALUE;
	static ArrayList<Point> house;
	static ArrayList<Point> chicken;
	static Point[] pick;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 도시의 크기 NxN
		M = Integer.parseInt(st.nextToken());	// 살아남는 치킨집 수
		
		house = new ArrayList<>();
		chicken = new ArrayList<>();
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				input = Integer.parseInt(st.nextToken());
				if (input == 1) {	// 집이면
					house.add(new Point(i, j));
				} else if (input == 2) {	// 치킨집이면
					chicken.add(new Point(i, j));
				}
			}
		}
		
		pick = new Point[M];	// 픽한 치킨집을 담을 배열
		dfs(0, 0);
		
		System.out.println(answer);
		br.close();
	}
	
	static void dfs(int start, int cnt) {
		if (cnt == M) {
			int sum = 0;
			
			for (Point h : house) {
				int tmp = Integer.MAX_VALUE;
				for (Point p : pick) {
					tmp = Math.min(tmp, Math.abs(h.x-p.x) + Math.abs(h.y-p.y));
				}
				sum += tmp;
			}
			
			if (answer>sum)	answer = sum;
			return;
		}
		
		for (int i=start; i<chicken.size(); i++) {
			pick[cnt] = chicken.get(i);
			dfs(i+1, cnt+1);
		}
	}
}
