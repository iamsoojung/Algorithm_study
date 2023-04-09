N = int(input())
info = []

for i in range(N):
    # info.append(input().split())
    age, name = map(str, input().split())
    age = int(age)
    info.append((age, name))

info.sort(key= lambda x:x[0])
    
for i in info:
    print(i[0], i[1])