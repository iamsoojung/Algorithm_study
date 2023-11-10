def solution(numbers, hand):
    answer = ''
    
    keypad = {
        '1' : (0,0), '2' : (0,1), '3' : (0,2),
        '4' : (1,0), '5' : (1,1), '6' : (1,2),
        '7' : (2,0), '8' : (2,1), '9' : (2,2),
        '*' : (3,0), '0' : (3,1), '#' : (3,2),
    }
    
    left = keypad['*']
    right = keypad['#']
    
    for n in numbers:
        if n == 1 or n == 4 or n == 7:
            answer += 'L'
            left = keypad[str(n)]
        elif n == 3 or n == 6 or n == 9:
            answer += 'R'
            right = keypad[str(n)]
        elif n == 2 or n == 5 or n == 8 or n == 0:
            d_l = abs(keypad[str(n)][0] - left[0]) + abs(keypad[str(n)][1] - left[1])
            d_r = abs(keypad[str(n)][0] - right[0]) + abs(keypad[str(n)][1] - right[1])
            
            if d_l < d_r:
                answer += 'L'
                left = keypad[str(n)]
            elif d_r < d_l:
                answer += 'R'
                right = keypad[str(n)]
            elif d_r == d_l:
                if hand == 'left':
                    answer += 'L'
                    left = keypad[str(n)]
                elif hand == 'right':
                    answer += 'R'
                    right = keypad[str(n)]
                
            
    return answer