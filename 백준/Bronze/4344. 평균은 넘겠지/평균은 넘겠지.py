C = int(input())

for i in range(C):
    cnt = 0
    num = list(map(int, input().split()))
    avg = sum(num[1:])/num[0]
    for j in range(num[0]):
        if num[j+1] > avg:
            cnt += 1
    print('%.3f%%'%(cnt/num[0]*100))