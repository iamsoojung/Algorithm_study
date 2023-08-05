from collections import Counter
import sys

N = int(sys.stdin.readline())
num = []
for _ in range(N):
    i = int(sys.stdin.readline())
    num.append(i)
num.sort()

# 산술평균
print(round(sum(num)/N))

# 중앙값
print(num[N//2])

# 최빈값
cnt = Counter(num).most_common(2)
    # 최빈값 여러개일때 두번째로 작은 값 출력 위함
if len(num) > 1:
    if cnt[0][1] == cnt[1][1]:  #  최빈값 여러개
        print(cnt[1][0])
    else:   # 최빈값 하나
        print(cnt[0][0])
else:
    print(cnt[0][0])

# 범위
print(max(num) - min(num))