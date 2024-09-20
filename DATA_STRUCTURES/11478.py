word = input()
set =set()
for i in range(len(word)):
    for j in range(i, len(word)):
        set.add(word[i : j + 1])

print(len(set))