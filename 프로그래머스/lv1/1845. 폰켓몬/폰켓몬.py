def solution(nums):
    answer = 0
    count = set()
    for num in nums:
        count.add(num)
    length = int(len(nums) / 2)
    
    return len(count) if len(count) <= length else length