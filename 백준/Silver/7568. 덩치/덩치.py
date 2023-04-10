N = int(input())
people = []

for i in range(N):
    people.append(list(map(int, input().split())))

# 자기보다 덩치 큰 인원 수+1 = 등수
for i in people:
    rank = 1
    for j in people:
        if i[0] < j[0] and i[1] < j[1]:
            rank += 1
    print(rank, end=" ")