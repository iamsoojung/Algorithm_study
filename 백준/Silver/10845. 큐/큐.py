import sys
N = int(sys.stdin.readline())

data = []
q = []

for n in range(N):
    data.append(list(map(str, sys.stdin.readline().split())))
    
    if data[n][0] == 'push':
        q.append(int(data[n][1]))
    elif data[n][0] == 'pop':
        if q:
            print(q.pop(0))
        else:
            print(-1)
    elif data[n][0] == 'size':
        print(len(q))
    elif data[n][0] == 'empty':
        if len(q) < 1:
            print(1)
        else:
            print(0)
    elif data[n][0] == 'front':
        if q:
            print(q[0])
        else:
            print(-1)
    elif data[n][0] == 'back':
        if q:
            print(q[-1])
        else:
            print(-1)