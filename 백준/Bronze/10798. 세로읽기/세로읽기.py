words = []
length = []
for _ in range(5):
    word = list(map(str, input()))
    words.append(word)
    length.append(len(word))

for i in range(max(length)):
    for j in range(5):
        if i < length[j]:
            print(words[j][i], end='')


# AABCDD
# afzz
# 09121
# a8EWg6
# P5h3kx