T = int(input())

for i in range(T):
    K = int(input())
    N = int(input())
    people = list(i for i in range(1, N+1))
    
    total = 0
    for k in range(K):
        for n in range(1, N):
            people[n] += people[n-1]
    print(people[-1])

            
# 1-3
# 1 / 2 / 3 (6)
    
# 2-3
# 1 / 2 / 3
# 1 / 1,2 / 1,2,3 (10)

# 3-3
# 1 / 2 / 3
# 1 / 1,2 / 1,2,3
# 1 / 1, 1,2 / 1, 1,2, 1,2,3 (15)

