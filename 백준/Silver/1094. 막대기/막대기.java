import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 64cm 막대기, 작은 막대로 잘라서 x cm 막대 만드려고 함
 * 1. 갖고있는 막대 길이를 모두 더한다. 처음에는 64cm 막대 하나만 갖고 있음 
 * 		(이때, 합 > x 라면, 아래를 반복)
 * 1) 가지고 있는 막대 중 가장 길이 짧은거를 절반으로 자름
 * 2) 위에서 자른 막대 절반 중, 하나 버리고 남아있는 막대의 길이 합 >= x라면,
 * 		위에서 자른 막대의 절반 중 하나 버린다.
 * 3) 남아있는 모든 막대를 풀로 붙여서 xcm 만든다
 * 
 * 몇개의 막대를 풀로 붙여서 x cm 만드는지 구하기
 */

public class Main {
	
	static int X;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		X = Integer.parseInt(br.readLine());
		
		int stick = 64;
		int count = 0;
		
		while (X > 0) {
			if (stick > X) {
				stick /= 2;
			} else {
				X -= stick;
				count++;
			}
		}
		
		System.out.println(count);
	}
}