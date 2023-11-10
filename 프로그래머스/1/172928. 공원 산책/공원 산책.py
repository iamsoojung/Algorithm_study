DIRS = [[-1,0], [0, 1], [1,0], [0, -1]]
DIR_MAP = {     # 동서남북에 따라 이동할 수 있도록 딕셔너리 생성
    'N' : 0,    
    'E' : 1,
    'S' : 2,
    'W' : 3,
}

class Dog:
    def __init__(self, park):
        self.park, self.rows, self.cols = park, len(park), len(park[0])
        
        # 이 중에서 'S'가 어디있는지 확인하기 위함
        for idx_r, r in enumerate(park):    # 행 단위
            for idx_c, c in enumerate(r):   # 열 단위
                if c == 'S':
                    self.r, self.c = idx_r, idx_c
                
    # routes = ["E 2", "S 2", "W 1"]
    # routes.split()[0] => "E"  /  routes.split()[0] => "2"
    def move(self, route):  # 이동함
        d = route.split()[0]    # 'E'
        steps = int(route.split()[1])   # 2
        
        dr, dc = DIRS[ DIR_MAP[ d ] ]
        
        safe = True
        
        nr, nc = self.r, self.c
        
        # 2 : range(1, 3) 1 ~ 2
        for i in range(1, steps + 1):
            nr += dr
            nc += dc
            
            if nr < 0 or nr >= self.rows or nc < 0 or nc >= self.cols or self.park[nr][nc] == 'X':
                safe = False
                break
        
        if not safe:
            return
        
        self.r, self.c = nr, nc
    
    def location(self):     # 위치 알려줌
        return [self.r, self.c]

def solution(park, routes):
    dog = Dog(park)
    
    for r in routes:
        dog.move(r)
    
    return dog.location()