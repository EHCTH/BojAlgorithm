arr = input()
ret = [0] * 26

for i in arr:
    ret[ord(i) - 97] += 1

print(*ret)