for _ in range(int(input())):
    n = int(input())
    array = []
    for _ in range(n):
        n, m = map(str, input().split())
        array.append([n,int(m)])
    answer = sorted(array, key = lambda x: x[1])
    print(answer[-1][0])