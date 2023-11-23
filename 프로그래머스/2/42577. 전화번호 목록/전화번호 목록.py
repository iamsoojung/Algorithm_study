def solution(phone_book):
    answer = True
    dict = {}
    
    for p in phone_book:
        dict[p] = 0
    
    for p in phone_book:
        check = ''
        for i in p:
            check += i
            if (check in dict) and (check != p):
                answer = False
                break
    
    return answer