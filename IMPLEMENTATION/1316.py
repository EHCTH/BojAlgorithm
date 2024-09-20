n  = int(input())
group_word = 0
for _ in range(n):
    word = input()
    error = 0
    for i in range(1, len(word)):
        if word[i - 1] != word[i]:
            new_word = word[i:]
            if new_word.count(word[i - 1]) > 0:
                error += 1
    if error == 0:
        group_word += 1
print(group_word)