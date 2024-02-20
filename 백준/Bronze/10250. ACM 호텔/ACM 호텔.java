import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int i=0; i<T; i++) {
		    int H = sc.nextInt();   // 층
		    int W = sc.nextInt();   // 방
		    int N = sc.nextInt();   // 손님
		    
		    if (N % H == 0) {
		        System.out.println((H*100) + (N/H));
		    } else {
		        System.out.println(((N%H) * 100) + ((N/H + 1)));
		    }
		}
	}
}
