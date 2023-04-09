hour, minute = map(int, input().split())
C = int(input())

hour += C//60
minute += C%60

if (minute >= 60):
    minute -= 60
    hour += 1

if (hour >= 24):
    hour -= 24

print(hour, minute)