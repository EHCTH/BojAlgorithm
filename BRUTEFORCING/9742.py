def mul(li):
    cal = 1
    for i in li:
        cal *= i
    return cal

def solutions(n, arr):
    global index, answer
    if answer:
        return

    if n == len(word):
        index += 1
        if index == key:
            print(f'{word} {key} = {arr}')
            answer = True
        return

    for i in range(len(word)):
        if not visited[i]:
            visited[i] = True
            solutions(n + 1, arr + word[i])
            visited[i] = False

while True:
    try:
        word, key = input().split()
        key = int(key)
        answer = False
        fact = mul([i for i in range(2, len(word) + 1)])
        index = 0
        visited = [False] * len(word)
        if key > fact:
            print(f'{word} {key} = No permutation')
        else:
            solutions(0, "")
    except:
        break