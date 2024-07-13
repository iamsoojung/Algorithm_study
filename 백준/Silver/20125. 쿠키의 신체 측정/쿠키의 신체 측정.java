import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;

class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		char[][] map = new char[N][N];
		
		String s;
		Point head = new Point(-1, -1);
		for (int i=0; i<N; i++) {
			s = br.readLine();
			map[i] = s.toCharArray();
			
			if (head.x == -1) {	// 아직 머리 저장 안됐으면
				for (int j=0; j<N; j++) {
					if (map[i][j] == '*') {
						head.x = i;
						head.y = j; 
					}
				}
			}
		}
		
		Point heart = new Point(head.x + 1, head.y);
		sb.append(heart.x+1).append(" ").append(heart.y+1).append("\n");
		
		int leftArm = 0, rightArm = 0, leftLeg = 0, rightLeg = 0, center = 0;
		for (int i=0; i<N; i++) {
			if (map[heart.x][i] == '*') {
				if (i < heart.y) {	// 심장 왼쪽 == 왼팔
					leftArm++;
				} else if (i > heart.y) {	// 심장 오른쪽 == 오른팔
					rightArm++;
				}
			}
		}
		
		for (int i=heart.x+1; i<N; i++) {
			if (map[i][heart.y] == '*') {
				center++;	// 허리
			}
			if (map[i][heart.y - 1] == '*') {
				leftLeg++;
			}
			if (map[i][heart.y + 1] == '*') {
				rightLeg++;
			}
		}
		
		sb.append(leftArm).append(" ").append(rightArm).append(" ").append(center).append(" ").append(leftLeg).append(" ").append(rightLeg);
		System.out.print(sb.toString());
	}
}
