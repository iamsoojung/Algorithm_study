# 0인 100*100 배열 생성
# 색종이 붙인 곳을 1로 변경
# 1인 요소 개수 = 면적
n = int(input())
paper = [[0]*100 for i in range(100)]
area = 0

for i in range(n):
    x, y= list(map(int, input().split()))
    
    for r in range(x, x+10):
        for w in range(y, y+10):
            paper[r][w] = 1

for p in paper:
    area += p.count(1)
print(area)