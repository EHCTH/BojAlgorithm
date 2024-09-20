dict = {'-': 0, '\\': 1, '(': 2, '@': 3, '?': 4, '>': 5, '&': 6, '%': 7, '/': -1}
while True:
    word = input()
    if word == '#':
        break
    res = 0
    for i in range(len(word)):
        res += dict[word[i]] * 8 ** (len(word) - i - 1)
    print(res)