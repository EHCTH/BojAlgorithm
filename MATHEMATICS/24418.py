import math
n = int(input())
graph = [list(map(int, input().split())) for _ in range(5)]
answer_1 = math.factorial(2 * n) // (math.factorial(n) ** 2)
answer_2 = n ** 2
print(answer_1, answer_2)