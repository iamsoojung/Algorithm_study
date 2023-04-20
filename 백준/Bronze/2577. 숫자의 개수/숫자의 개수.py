A = int(input())
B = int(input())
C = int(input())

result = A * B * C
num = [0]*10

for i in str(result):
    num[int(i)] += 1

for j in range(10):
    print(num[j])