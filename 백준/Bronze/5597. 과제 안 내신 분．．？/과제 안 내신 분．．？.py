everyone = [i for i in range(1,31)]
submit = []
nosubmit = []

for i in range(28):
    submit.append(int(input()))

nosubmit = [i for i in everyone if i not in submit]
print(min(nosubmit))
print(max(nosubmit))