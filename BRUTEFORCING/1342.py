word = list(input())
cnt = 0
alphabet = [0] * 26
for i in range(len(word)):
    alphabet[ord(word[i]) - ord('a')] += 1

def solutions(n, s, pr):
    global cnt
    if n == len(word):
        cnt += 1
        return
    for i in set(word):
        if alphabet[ord(i) - ord('a')] != 0 and i != s:
            alphabet[ord(i) - ord('a')] -= 1
            solutions(n + 1, i, pr + i)
            alphabet[ord(i) - ord('a')] += 1
solutions(0, '', '')
print(cnt)