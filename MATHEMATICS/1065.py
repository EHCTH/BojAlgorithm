a = int(input())
def check(s):
    count = 0
    for i in range(len(s) - 1):
        if not count:
            total = int(s[i]) - int(s[i + 1])
            count += 1
        elif total == int(s[i]) - int(s[i + 1]):
            continue
        else:
            return False
    return True
answer = 0
for i in range(1, a + 1):
    s = list(map(int, str(i)))
    if i < 100:
        answer += 1
    else:
        if check(s):
            answer += 1
print(answer)