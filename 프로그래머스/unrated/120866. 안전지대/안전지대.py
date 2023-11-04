def solution(board):
    N = len(board)
    M = len(board[0])
    answer = N * M
    
    for x in range(N):
        for y in range(M):
            if board[x][y] == 1:    # 현재 칸에 지뢰가 있는지
                answer -= 1
                
            else:            
                for dx, dy in ((-1,0), (1,0), (0,-1), (0,1), (-1, -1), (-1,1), (1, -1), (1, 1)):
                    nx = x + dx
                    ny = y + dy
                    
                    if 0<=nx<N and 0<=ny<M and board[nx][ny] == 1:
                        answer -= 1
                        break
    
    return answer