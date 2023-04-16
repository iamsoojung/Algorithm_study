import sys
M = int(sys.stdin.readline())
S = set()   # 집합
for i in range(M):
    tmp = sys.stdin.readline().strip().split()
    
    if len(tmp) == 1:
        if tmp[0] == "all":
            S = set([i for i in range(1, 21)])
        else:   # tmp[0] == "empty"
            S = set()
    
    else:
        op, x = tmp[0], tmp[1]
        x = int(x)
        
        if op=="add":
            S.add(x)
        elif op=="remove":
            S.discard(x)    # 없는 수를 지우려고 해도 오류 X
        elif op=="check":
            print(1 if x in S else 0)
        elif op=="toggle":
            if x in S:
                S.discard(x)
            else:
                S.add(x)