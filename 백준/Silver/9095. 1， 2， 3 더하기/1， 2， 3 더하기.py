# 1 - 1 (1)
# 2 - 11, 2 (2)
# 3 - 111, 12, 21, 3 (4)
# 4 - 1111, 112,121,211, 31,13, 22 (7)
# 5 - 11111, 1112,1121,1211,2111, 113,131,311, 221,212,122, 32,23 (13)
# 따라서 A(i) = A(i-1)+A(i-2)+A(i-3) (i>3)

def dfs(n):
    if n == 1:
        return 1
    elif n == 2:
        return 2
    elif n == 3:
        return 4
    else:
        return dfs(n-1) + dfs(n-2) + dfs(n-3)

T = int(input())
for _ in range(T):
    N = int(input())
    print(dfs(N))