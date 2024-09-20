def solutions(n, start, end):
    if n == 0:
        return
    else:
        mid = (end - start + 1) // 3
        solutions(n - 1, start, start + mid - 1) # 왼쪽 0, 9 - 1
        for i in range(start + mid, start + mid * 2): # 중간 공백
            answer[i] = " "
        solutions(n - 1, start + mid * 2, start + mid * 3 - 1) # 오른쪽 18 ~ 27 - 1
while True:
    try:
        n = int(input())
        answer = ["-"] * 3 ** n
        solutions(n, 0, 3 ** n -1)
        print("".join(answer))
    except:
        break