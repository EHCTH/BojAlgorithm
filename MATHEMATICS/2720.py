for i in range(int(input())):
    money = int(input())
    c = [25, 10, 5, 1]
    for i in c:
        print(money // i, end=" ")
        money %= i