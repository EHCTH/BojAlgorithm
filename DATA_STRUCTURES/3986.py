def solutions(word):
    global answer
    stack = []
    for i in range(len(word)):
        if stack:
            if word[i] == 'A':
                if stack[-1] == 'A':
                    stack.pop()
                else:
                    stack.append(word[i])

            else:
                if stack[-1] == 'B':
                    stack.pop()
                else:
                    stack.append(word[i])
        else:
            stack.append(word[i])
    if not stack:
        answer += 1
    return False
answer = 0
for T in range(int(input())):
    word = input()
    solutions(word)
print(answer)