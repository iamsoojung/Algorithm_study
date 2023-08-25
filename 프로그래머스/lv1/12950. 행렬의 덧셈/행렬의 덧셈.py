# 완전 탐색 (brute force)
def solution(arr1, arr2):
    
    row = len(arr1) # 2
    col = len(arr1[0])  # 2
    
    # [[0,0], [0,0]]
    answer = [[0] * col for _ in range(row)]
    
    # 로직 (2중 for문 -> row랑 col로)
    for i in range(row):
        for j in range(col):
            # arr1[1][1] + arr2[1][1] 크기가 같아서 되는 것
            answer[i][j] = arr1[i][j] + arr2[i][j]
    
    return answer