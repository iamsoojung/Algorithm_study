T = int(input())
for i in range(T):
    stack = []
    vps = input()
    for j in vps:
        if j == '(':
            stack.append(j)
        elif j == ')':
            if stack:
                stack.pop()
            else:   # 스택에 괄호 없으면
                print("NO")
                break
    else: 
        if not stack:
            print("YES")
        else:
            print("NO")