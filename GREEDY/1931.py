n = int(input())
array = [list(map(int, input().split())) for _ in range(n)]
array.sort(key = lambda x: (x[1], x[0]))
pastI, pastJ = array[0][0], array[0][1]
new_array = [item for item in array[1:] if pastJ <= item[0]]
cnt = 1
for i, j in new_array:
    if i >= pastJ:
        pastJ = j
        cnt += 1
print(cnt)