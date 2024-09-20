arr = [1, 5, 10, 50]
N = int(input())
li = [0] * 100001
def solutions(n, num, sm):
    if n == N:
        if not li[sm]:
            li[sm] = 1
        return
    for i in range(num, len(arr)):
        solutions(n + 1, i, sm + arr[i])
solutions(0, 0, 0)
print(sum(li))