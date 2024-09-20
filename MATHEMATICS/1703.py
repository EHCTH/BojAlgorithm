while True:
    li = list(map(int, input().split())) 
    answer = 1
    if li[0] == 0:       
        break
    for i in range(1, len(li), 2):
        answer = answer * li[i] - li[i+1]
    print(answer)