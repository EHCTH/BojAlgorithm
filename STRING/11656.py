word = input()
answer = []
for i in range(len(word)):
    answer.append(word[i:])
for i in sorted(answer):
    print(i)