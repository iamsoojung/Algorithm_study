# 홀수 : 3(01,10), 5(012,234), 7(0123,3456)
# 짝수 : 2(0,1), 4(01,23), 6(012,345)
# round() 함수는 올림, 내림의 결과값이 같을 경우 짝수로 반올림함(쭈의!)

import math
while(1):
    n = list(map(int, input()))
    if n[0]==0:    
        break
    if len(n)%2==0:
        reverse_n = list(reversed(n[math.ceil(len(n)/2):]))
    else:
        reverse_n = list(reversed(n[math.ceil(len(n)/2)-1:]))
    if n[:math.ceil(len(n)/2)]==reverse_n:
        print("yes")
    else:
        print("no")