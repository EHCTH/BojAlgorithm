n = int(input())
word = list(input())

for _ in range(n - 1):
    word2 = input()
    for i in range(len(word)):
        if word[i] == word2[i]:
            continue
        else:
            word[i] = "?"
print(*word, sep = "")