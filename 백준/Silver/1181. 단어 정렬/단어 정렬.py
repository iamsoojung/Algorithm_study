N = int(input())
word = []
result = []

for i in range(N):
    word.append(input())

word = list(set(word))

for i in word:
    result.append((len(i), i))
    
result = sorted(result)

for len_i, i in result:
    print(i)