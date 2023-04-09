N, M = map(int, input().split())
box = [i for i in range(1, N+1)]

for i in range(M):
    begin, end, mid = map(int, input().split())
    box = box[:begin-1] + box[mid-1:end] + box[begin-1:mid-1] + box[end:]
    
for i in range(len(box)):
    print(box[i], end=" ")