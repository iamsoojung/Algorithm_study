import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	
	static class Student implements Comparable<Student> {
		int num, likeCnt, uploadTime;
		
		public Student(int num, int likeCnt, int uploadTime) {
			super();
			this.num = num;
			this.likeCnt = likeCnt;
			this.uploadTime = uploadTime;
		}

		@Override
		public int compareTo(Student o) {
			if (this.likeCnt == o.likeCnt) {
				return o.uploadTime - this.uploadTime;
			}
			return o.likeCnt - this.likeCnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		
		Student[] students = new Student[101];
		ArrayList<Student> photos = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			
			if (students[num] != null) {
				students[num].likeCnt++;
			} else {
				Collections.sort(photos);
				
				// 꽉차면 마지막 인덱스 데이터 삭제
				if (photos.size() == N) {
					Student del = photos.remove(N-1);
					students[del.num] = null;
				}
				
				// 새로운 학생 등록
				students[num] = new Student(num, 1, i);
				photos.add(students[num]);
			}
		}
		
		Collections.sort(photos, (o1, o2) -> o1.num - o2.num);
		
		for (Student s : photos) {
			sb.append(s.num + " ");
		}
		System.out.print(sb.toString());
	}
}
