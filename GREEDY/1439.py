word = input()
cnt = 0
for i in range(len(word) - 1):
    if word[i] != word[i + 1]:
        cnt += 1
print((cnt + 1) // 2)