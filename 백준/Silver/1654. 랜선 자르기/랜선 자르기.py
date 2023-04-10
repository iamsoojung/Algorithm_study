K, N = map(int, input().split())
Lan = []
for i in range(K):
    Lan.append(int(input()))
    
# 최소길이:1, 최대길이:가장 긴 랜선
start=1
end=max(Lan)

while(start<=end):
    mid = (start+end)//2    # 평균길이부터 시작
    cnt = 0

    for i in Lan:
        cnt += i//mid
    
    if cnt>=N:  # N개를 만들었으면
        start = mid+1
    else:
        end = mid-1

print(end)