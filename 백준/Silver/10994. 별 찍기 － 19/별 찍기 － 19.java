import java.util.Scanner;
public class Main
{
    public static char star[][];
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		n = 4*n - 3;
		star = new char[n][n];
		
		for (int i=0; i<n; i++) {
		    for (int j=0; j<n; j++) {
		        star[i][j] = ' ';
		    }
		}
		make_star(0, n);
		
		for (int i=0; i<n; i++) {
		    for (int j=0; j<n; j++) {
		        System.out.print(star[i][j]);
		    }
		    System.out.println();
		}
	}
	public static void make_star(int start, int size) {
	    if (start >= size) {
	        return;
	    } else {
	        for (int i=start; i<size; i++) {
	            star[start][i] = '*';   // 위
	            star[i][start] = '*';   // 왼
	            star[i][size-1] = '*';  // 오
	            star[size-1][i] = '*';  // 아
	        }
	        make_star(start+2, size-2);
	    }
	}
}

// 1 -> 1 / 2 -> 5 / 3 -> 9 => 4n - 3