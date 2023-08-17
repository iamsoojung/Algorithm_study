# 15649와는중복을 허용하지 않는 차이점

# def dfs():
#     if len(s) == m:
#         print(' '.join(map(str, s)))
#         return
#     for i in range(1, n+1):
#         if i not in s:
#             s.append(i)
#             dfs()
#             s.pop()

def dfs(start):
    if len(s) == m:
        print(' '.join(map(str, s)))
        return
    for i in range(start, n+1):
        if i not in s:
            s.append(i)
            dfs(i+1)
            s.pop()

n, m = list(map(int, input().split()))

s = []
dfs(1)