for _ in range(int(input())):
    n = int(input())
    answer = 0 
    for i in range(1, n):
        if i ** 2 <= n:
            answer += 1
        if i ** 2 > n:
            break
    print(answer)