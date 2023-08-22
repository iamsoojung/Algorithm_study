def solution(numbers):
    my_list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
    # 길이가 10개짜리인 빈 배열을 만들어서 해당 숫자에 값이 존재하면 카운팅을 해서 카운팅이 되지 않은 배열 내 요소를 합하면 됩니다.
    
    for num in numbers:
        my_list.remove(num)
    
    return sum(my_list)