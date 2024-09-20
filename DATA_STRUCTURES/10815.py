import collections
dict = collections.defaultdict(int)

n = int(input())
array1 = list(map(int, input().split()))
for i in array1:
    dict[i] = 1
m = int(input())
array2 = list(map(int, input().split()))
answer = [dict[i] for i in array2]
print(*answer)