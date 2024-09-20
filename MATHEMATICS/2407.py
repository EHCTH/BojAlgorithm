n, k = map(int, input().split())
def factorial(digit):
    answer = 1
    for i in range(1, digit + 1):
        answer *= i
    return answer
n1 = factorial(n)
n2 = factorial(n - k)
k = factorial(k)
print(n1// (n2 * k))