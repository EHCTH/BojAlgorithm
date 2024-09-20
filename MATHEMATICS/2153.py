word = input()
cnt = 0
for w in word:
    if w.islower():
        cnt += ord(w) - 96
    else:
        cnt += ord(w) - 38

def solution(cnt):
    if (cnt == 1):
        return "It is a prime word."
    for i in range(2, int((cnt ** (1 / 2))) + 1):
        if cnt % i == 0:
            return "It is not a prime word."
    return "It is a prime word."
print(solution(cnt))