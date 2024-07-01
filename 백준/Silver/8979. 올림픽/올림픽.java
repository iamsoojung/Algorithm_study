import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Main {
	
	static class Medal implements Comparable<Medal>{
		int num, gold, silver, bronze, rank;
		
		public Medal(int num, int gold, int silver, int bronze, int rank) {
			super();
			this.num = num;
			this.gold = gold;
			this.silver = silver;
			this.bronze = bronze;
			this.rank = rank;
		}
		
		@Override
		public int compareTo(Medal o) {
			if (this.gold != o.gold) {
				return o.gold - this.gold;
			} else if (this.silver != o.silver) {
				return o.silver - this.silver;
			} else {
				return o.bronze - this.bronze;
			}
		}
	}
	
	static int N, K, answer = 0;
	static ArrayList<Medal> medalList;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());	// 국가의 수
		K = Integer.parseInt(st.nextToken());	// 등수를 알고 싶은 국가
		
		medalList = new ArrayList<>();
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int gold = Integer.parseInt(st.nextToken());
			int silver = Integer.parseInt(st.nextToken());
			int bronze = Integer.parseInt(st.nextToken());
			medalList.add(new Medal(num, gold, silver, bronze, 0));
		}
		Collections.sort(medalList);
		
		medalList.get(0).rank = 1;
		
		for (int i=1; i<N; i++) {
			Medal before = medalList.get(i-1);
			Medal now = medalList.get(i);
			
			if (now.num == K) {
				answer = i;		// 등수
				break;
			}
			
			if (now.gold == before.gold 
					&& now.silver == before.silver
					&& now.bronze == before.bronze) {	// 동일 순위
				now.rank = before.rank;
			} else {
				now.rank = i+1;
			}
		}
		System.out.println(answer);
	}
}