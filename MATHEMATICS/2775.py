for T in range(int(input())):
    k = int(input())
    n = int(input())
    graph = [i for i in range(n + 1)]
    for _ in range(k):
        for j in range(1, n + 1):
            graph[j] = graph[j - 1] + graph[j]
    print(graph[-1])