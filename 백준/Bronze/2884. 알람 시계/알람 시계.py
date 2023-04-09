H, M = input().split()
H = int(H)
M = int(M)

if (H < 0 or H > 24) :
    H = int(input())
    M = int(input())
if (M < 0 or M > 60) :
    H = int(input())
    M = int(input())

M -= 45
if (M < 0) :
    M += 60
    H -= 1
if (H < 0) :
    H += 24

print(H, M)