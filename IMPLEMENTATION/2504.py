word = input()


def solutions(word):
    stack = []
    answer, mul = 0, 1
    for i in range(len(word)):
        if word[i] == '[':
            mul *= 3
            stack.append(word[i])
        elif word[i] == '(':
            mul *= 2
            stack.append(word[i])
        elif word[i] == ')':
            if stack and stack[-1] == '(':
                if word[i - 1] == '(':
                    answer += mul
                mul //= 2
                stack.pop()
            else:
                return 0
        else:
            if stack and stack[-1] == '[':
                if word[i - 1] == '[':
                    answer += mul
                mul //= 3
                stack.pop()
            else:
                return 0
    if not stack:
        return answer
    return 0
print(solutions(word))