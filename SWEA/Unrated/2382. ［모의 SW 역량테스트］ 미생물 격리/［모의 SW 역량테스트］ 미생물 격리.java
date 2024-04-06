import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static class Node implements Comparable<Node>{
		int idx, x, y, w, dir;

		public Node(int idx, int x, int y, int w, int dir) {
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.w = w;
			this.dir = dir;
		}

		@Override
		public int compareTo(Node o) {
			if (this.idx == o.idx) {
				return o.w - this.w;	// 내림차순
			}
			return this.idx - o.idx;
		}
	}
	
	static int N, M, K, answer;	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	static List<Node> list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int testCase=1; testCase<=T; testCase++) {			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());	// 셀 개수
			M = Integer.parseInt(st.nextToken());	// 격리 시간
			K = Integer.parseInt(st.nextToken());	// 미생물 군집 수
			
			list = new ArrayList<>();
			for (int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken()) - 1;
				
				list.add(new Node(N*x+y, x, y, w, dir));
			}
			
			for (int m=0; m<M; m++) {
				move();
				mix();
			}
			
			sb.append("#").append(testCase).append(" ").append(calc()).append("\n");
		}
		System.out.print(sb.toString());
	}
	
	static void move() {
		for (int idx=0; idx<list.size(); idx++) {
			Node cur = list.get(idx);
			cur.x += dx[cur.dir];
			cur.y += dy[cur.dir];
			cur.idx = cur.x * N + cur.y;
			
			if (checkRange(cur.x, cur.y)) {
				cur.w /= 2;
				cur.dir = changeDir(cur.dir);

				if (cur.w == 0) {	// 0개면 리스트에서 삭제
					list.remove(idx);
					idx--;
				}
			}
		}
		Collections.sort(list);
	}
	
	static void mix() {
		for (int idx=0; idx<list.size()-1; idx++) {
			Node cur = list.get(idx);
			Node next = list.get(idx+1);
			
			if (cur.idx == next.idx) {
				cur.w += next.w;	// 합치기
				list.remove(idx+1);
				idx--;
			}
		}
	}
	
	static int calc() {
		int answer = 0;
		for (int i=0; i<list.size(); i++) {
			answer += list.get(i).w;
		}
		return answer;
	}
	
	static int changeDir(int dir) {
		switch(dir) {
		case 0:	return 1;
		case 1:	return 0;
		case 2:	return 3;
		case 3:	return 2;
		}
		return -1;
	}
	
	static boolean checkRange(int x, int y) {
		if (x==0 || x==N-1 || y==0 || y==N-1) {	// 가장자리 (약품)
			return true; 
		} return false;
	}
}
