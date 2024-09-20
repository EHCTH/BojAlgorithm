word = input()
stack = []
answer = 0
for i in range(len(word)):
    if word[i] == '(':
        stack.append(word[i])
    else:
        if word[i - 1] == '(':
            stack.pop()
            answer += len(stack)
        else:
            stack.pop()
            answer += 1
print(answer)