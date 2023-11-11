def solution(survey, choices):
    answer = ''
    mbti = {"R": 0, "T": 0, "C": 0, "F": 0, "J": 0, "M": 0, "A": 0, "N": 0}
    
    for i, (s, c) in enumerate(zip(survey, choices)):
        diff = c - 4
        if diff < 0:
            mbti[s[0]] += abs(diff)
        elif diff > 0:
            mbti[s[1]] += diff
    
    answer += "T" if mbti["R"] < mbti["T"] else "R"
    answer += "F" if mbti["C"] < mbti["F"] else "C"
    answer += "M" if mbti["J"] < mbti["M"] else "J"
    answer += "N" if mbti["A"] < mbti["N"] else "A"
    
    return answer