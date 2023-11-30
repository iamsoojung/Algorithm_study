# numbers 원소를 str 타입으로 변환 (이어붙일 수 있도록)
# numbers 원소는 1000 이하 => 따라서 모든 원소를 3배 (수를 3배 X, 문자열자체를 3배)

def solution(numbers):
    numbers = list(map(str, numbers))
    numbers.sort(key= lambda x:x*3, reverse=True)
    
    return str(int(''.join(numbers)))