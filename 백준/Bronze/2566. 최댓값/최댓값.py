max_num = 0
r, c = 1, 1
for i in range(9):
    num = list(map(int, input().split()))
    if max(num) > max_num:
        max_num = max(num)
        r = i+1
        c = num.index(max(num))+1
print(max_num)
print(r, c)