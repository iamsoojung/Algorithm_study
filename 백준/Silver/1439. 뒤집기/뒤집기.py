# s = input()
# count = 0

# for i in range(len(s)-1):
#   if (s[i] != s[i+1]):
#     count += 1

# if (count < 2):
#   print(count)
# else:
#   print(int(count/2))

s = input()
count_0 = 0
count_1 = 0

if s[0] == '0':
  count_1 += 1
else:
  count_0 += 1

for i in range(len(s)-1):
  if (s[i] != s[i+1]):
    if (s[i+1] == '0'):
      count_1 += 1
    else:
      count_0 += 1

print(min(count_0, count_1))