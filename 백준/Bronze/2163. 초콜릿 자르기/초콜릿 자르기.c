#include <stdio.h>
int main() {
    int N=0, M=0, result=0;
    scanf("%d %d", &N, &M);

    result = (N-1)+N*(M-1);
    printf("%d", result);
}