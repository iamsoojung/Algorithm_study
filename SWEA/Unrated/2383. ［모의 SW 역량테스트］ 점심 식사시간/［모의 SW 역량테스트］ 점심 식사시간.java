import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static class Node implements Comparable<Node> {
		int x, y, dist, time;
		
		public Node(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}

		@Override
		public int compareTo(Node o) {
			return this.dist - o.dist;
		}
	}

	static int N, people, answer; // people : 사람 수
	static int[][] map;
	static boolean[] select;
	static ArrayList<Node> person; // 사람 좌표
	static Node[] stair = new Node[2]; // 계단 좌표
	static ArrayList<Node> q1;
	static ArrayList<Node> q2;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int testCase = 1; testCase <= T; testCase++) {
			N = Integer.parseInt(br.readLine());

			map = new int[N][N];
			person = new ArrayList<>();

			int idx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						person.add(new Node(i, j, 0));
					} else if (map[i][j] > 1) {
						stair[idx++] = new Node(i, j, map[i][j]);
					}
				}
			}

			answer = Integer.MAX_VALUE;
			people = person.size();
			select = new boolean[people];
			subset(0);

			sb.append("#").append(testCase).append(" ").append(answer).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void subset(int cnt) {
		if (cnt == people) {
			move();
			return;
		}

		select[cnt] = true; // 1번 계단
		subset(cnt + 1);
		select[cnt] = false; // 2번 계단
		subset(cnt + 1);
	}

	static void move() {
		q1 = new ArrayList<>();
		q2 = new ArrayList<>();

		for (int i = 0; i < people; i++) {
			if (select[i]) {
				int dist = calcDist(person.get(i), 0) + 1; // 거리 계산 (1분후 내려가기 가능)
				q1.add(new Node(person.get(i).x, person.get(i).y, dist));
			} else {
				int dist = calcDist(person.get(i), 1) + 1; // 거리 계산
				q2.add(new Node(person.get(i).x, person.get(i).y, dist));
			}
		}

		Collections.sort(q1);
		Collections.sort(q2);

		// q1 처리
		for (int i = 0; i < q1.size(); i++) {
			if (i < 3) {
				q1.get(i).dist += stair[0].dist;
			} else {
				if (q1.get(i-3).dist <= q1.get(i).dist) {
					q1.get(i).dist += stair[0].dist;
				} else {	// 도착했는데도 3번째 앞사람이 내려가고 있으므로
					q1.get(i).dist = q1.get(i-3).dist + stair[0].dist;
				}
			}
		}

		// q2 처리
		for (int i = 0; i < q2.size(); i++) {
			if (i < 3) {
				q2.get(i).dist += stair[1].dist;
			} else {
				if (q2.get(i-3).dist <= q2.get(i).dist) {
					q2.get(i).dist += stair[1].dist;
				} else {	// 도착했는데도 3번째 앞사람이 내려가고 있으므로
					q2.get(i).dist = q2.get(i-3).dist + stair[1].dist;
				}
			}
		}

		answer = Math.min(answer, calcTime());
		return;
	}
	
	static int calcTime() {
		// 계단 1, 2 다 내려간거(마지막꺼) 중에 더 큰 것
		int t1 = 0, t2 = 0;
		if (!q1.isEmpty()) {
			t1 = q1.get(q1.size()-1).dist;
		}
		if (!q2.isEmpty()) {
			t2 = q2.get(q2.size()-1).dist;
		}
		return Math.max(t1, t2);
	}

	static int calcDist(Node cur, int sNum) {
		return Math.abs(cur.x - stair[sNum].x) + Math.abs(cur.y - stair[sNum].y);
	}
}