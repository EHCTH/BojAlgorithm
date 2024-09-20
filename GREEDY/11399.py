n = int(input())
array = list(map(int, input().split()))
array.sort()
for i in range(1, n):
    array[i] = array[i - 1] + array[i]
print(sum(array))