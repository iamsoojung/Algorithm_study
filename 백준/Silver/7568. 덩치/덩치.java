import java.util.Scanner;
public class Main
{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] spec = new int[N][2];
		
		for (int i=0; i<N; i++) {
		    spec[i][0] = sc.nextInt();
		    spec[i][1] = sc.nextInt();
		}
		
		for (int i=0; i<N; i++) {
		    int rank = 1;
		    
		    for (int j=0; j<N; j++) {
		        if (i==j)   continue;
		        if (spec[i][0] < spec[j][0] && spec[i][1] < spec[j][1]) {
		            rank++;
		        }
		    }
    		System.out.print(rank + " ");
		}
	}
}
