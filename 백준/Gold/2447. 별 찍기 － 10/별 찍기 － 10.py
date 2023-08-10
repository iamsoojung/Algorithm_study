import sys

def star(n):
    if n == 3:
        return ['***', '* *', '***']
    
    arr = star(n//3)
    stars = []
    
    for i in arr:
        stars.append(i*3)
    
    for i in arr:
        stars.append(i + ' '*(n//3) + i)
    
    for i in arr:
        stars.append(i*3)
    
    return stars

N = int(sys.stdin.readline())

print('\n'.join(star(N)))