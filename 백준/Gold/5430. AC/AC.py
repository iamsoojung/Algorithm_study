import sys
from collections import deque

T = int(sys.stdin.readline())

for t in range(T):
    p = sys.stdin.readline().strip()
    n = int(sys.stdin.readline())
    dq = deque(input().strip()[1:-1].split(','))
    
    if n == 0:
        dq = deque()
    
    R_flag = 0
    D_flag = 1
    for i in p:
        if i == 'R':
            R_flag += 1
        elif i == 'D':
            if dq:
                if R_flag % 2 == 0:
                    dq.popleft()
                else:
                    dq.pop()
            else:
                print('error')
                D_flag = 0
                break
        
    if D_flag == 0:
        continue
    else:
        if R_flag % 2:
            dq.reverse()

    print("[" + ",".join(dq) + "]")