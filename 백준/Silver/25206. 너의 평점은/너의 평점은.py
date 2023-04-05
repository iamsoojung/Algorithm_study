import sys

score_list = ['A+', 'A0', 'B+', 'B0', 'C+', 'C0', 'D+', 'D0','F']
num_list = [4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0.0]
sum = 0
count = 0

for i in range(20):
    _, num, score = sys.stdin.readline().split()
    
    if score=='P':  
        continue
    
    num = float(num)
    count += num
    idx = score_list.index(score)
    sum += num_list[idx] * num
    
print('%.6f'%(sum/count))