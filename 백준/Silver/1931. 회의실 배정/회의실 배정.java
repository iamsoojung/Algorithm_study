import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Meeting implements Comparable<Meeting>{
		int start, end;
		
		Meeting (int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting o) {
			return this.end != o.end ? this.end-o.end : this.start-o.start;
		}

		@Override
		public String toString() {
			return "Meeting [start=" + start + ", end=" + end + "]";
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Meeting[] meetings = new Meeting[N];	// 회의 개수 만큼 배열 생성
		
		StringTokenizer st;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings);
		
		// 회의 선택을 최대로 하고 선택된 회의들의 내용을 출력
		List<Meeting> list = new ArrayList<>();
		list.add(meetings[0]);	// 첫 회의 선택
		for (int j=1; j<N; j++) {	// j : 하나씩 고려하는 회의
			// 마지막 회의의 종료 시간 보다 현재 고려하는 회의의 시작 시간이 커야함
			if (list.get(list.size()-1).end <= meetings[j].start) {
				list.add(meetings[j]);
			}
		}
		
		System.out.print(list.size());	// 회의 갯수
	}
}
