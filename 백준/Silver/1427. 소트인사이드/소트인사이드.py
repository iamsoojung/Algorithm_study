N = int(input())

num = []
for i in str(N):
    num.append(int(i))
    # num = list(map(int, str(N)))

num.sort(reverse=True)

for j in range(len(num)):
    print(num[j], end='')