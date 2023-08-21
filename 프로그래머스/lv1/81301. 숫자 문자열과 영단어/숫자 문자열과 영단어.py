def solution(s):
    dic = {'zero':'0', 'one':'1', 'two':'2', 'three':'3', 'four':'4', 'five':'5', 'six':'6', 'seven':'7', 'eight':'8', 'nine':'9'}
    answer = ''
    tmp = ''
    
    for i in range(len(s)):
        if s[i].isdigit():
            answer += s[i]
        elif s[i].isalpha():
            tmp += s[i]
            if tmp in dic:
                answer += dic[tmp]
                tmp = ''

    return int(answer)
      