import java.util.Scanner;
public class Main
{
    public static int[] arr;
    public static int[] tmp;
    public static int k;
    public static int check;
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        k = sc.nextInt();
        
        arr = new int[n];
        tmp = new int[n];
        for (int i=0; i<n; i++) {
            arr[i] = sc.nextInt();
        }
        
        merge_sort(0, n-1); // 시작과 끝
        
        System.out.println(-1); // 저장 횟수 < k 일때
    }
    
    // A[p..r]을 오름차순 정렬한다.
    public static void merge_sort(int p, int r) {
        int q;
        if (p<r) {
            q = (p+r) / 2;          // q는 p, r의 중간 지점
            merge_sort(p, q);    // 전반부 정렬
            merge_sort(q+1, r);  // 후반부 정렬
            merge(p, q, r);      // 병합
        }
    }
    
    // A[p..q]와 A[q+1..r]을 병합하여 A[p..r]을 오름차순 정렬된 상태로 만든다.
    // A[p..q]와 A[q+1..r]은 이미 오름차순으로 정렬되어 있다.
    public static void merge(int p, int q, int r) {
        int i = p;
        int j = q+1;
        int t = 0;      // 배열 인덱스에 맞추어 1 아닌 0 저장
        
        while (i<=q && j<=r) {
            if (arr[i] <= arr[j]) {
                tmp[t++] = arr[i++];
            } else {
                tmp[t++] = arr[j++];
            }
        }  
        
        while (i<=q) {  // 왼쪽 배열 부분이 남은 경우
            tmp[t++] = arr[i++];
        }
        while (j<=r) {  // 오른쪽 배열 부분이 남은 경우
            tmp[t++] = arr[j++];
        }
        
        i = p;
        t = 0;      // 배열 인덱스에 맞추어 1 아닌 0 저장
        while (i<=r) {  // 결과를 A[p..r]에 저장
            arr[i++] = tmp[t++];
            check++;
            if (check == k) {
                System.out.println(arr[i-1]);
                System.exit(0);
            }
        }
    }
}
