word = input().upper()
word_list = list(set(word))
cnt_list = []

for i in word_list:
    cnt = word.count(i)
    cnt_list.append(cnt)

if cnt_list.count(max(cnt_list)) >1:
    print("?")
else:
    print(word_list[cnt_list.index(max(cnt_list))])