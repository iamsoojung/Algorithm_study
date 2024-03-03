import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static class Project implements Comparable<Project>{ 
		int start, end;

		public Project(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Project o) {
			if (this.start != o.start) {
				return this.start - o.start;
			}
			return o.end - this.end;
		}
	}
	
	static int N, answer = 0;
	static ArrayList<Project> p = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());	// 참여 가능한 프로젝트들의 총 개수
		
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int sM = Integer.parseInt(st.nextToken());
			int sD = Integer.parseInt(st.nextToken());
			int eM = Integer.parseInt(st.nextToken());
			int eD = Integer.parseInt(st.nextToken());
			
			p.add(new Project(sM*100+sD, eM*100+eD));
		}
		
		Collections.sort(p);
		
		int s = 301;
		int e = 1201;

		boolean flag = false;
		int idx = 0;
		int tmpEnd = 0;
		int cnt = 0;
		
		while (s < e) {
			flag = false;
			for (int i=idx; i<N; i++) {
				if (s < p.get(i).start)	break;	// 처음부터 공백이 생겨버림
				
				if (tmpEnd < p.get(i).end) {
					tmpEnd = p.get(i).end;
					idx = i+1;
					flag = true;
				}
			}
			if (flag) {
				cnt++;
				s = tmpEnd;
			} else	break;
		}
		
		if (e <= tmpEnd) {
			System.out.println(cnt);
		} else {	// 어느순간부터 끝까지 공백이 생겨버림
			System.out.println(0);
		}
	}
}
