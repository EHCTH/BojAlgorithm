arr = ['a', 'e', 'i', 'o', 'u']
while True:
    cnt = 0
    word = input()
    if word == '#':
        break
    for s in word:
        if s.lower() in arr:
            cnt += 1
    print(cnt)