alpha = ['ABC', 'DEF', 'GHI', 'JKL', 'MNO', 'PQRS', 'TUV', 'WXYZ' ]
call = input()
time = 0

for c in call:
    for a in alpha:
        if a.find(c) >= 0:
            time += alpha.index(a) + 3
print(time)