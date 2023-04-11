X = int(input())
line, index = 0, 0
while X > line:
    index += 1
    line += index
    
    if index%2==0:
        a = index - (line-X)
        b = (line-X) + 1
    else:
        a = (line-X) + 1
        b = index - (line-X)

print('%d/%d'%(a,b))