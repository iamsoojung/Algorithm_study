word = ['c=', 'c-', 'dz=', 'd-', 'lj', 'nj', 's=', 'z=']
alpha = input()

for i in word:
    alpha = alpha.replace(i, 'a')

print(len(alpha))