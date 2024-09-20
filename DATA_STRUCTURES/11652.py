arr = []
for T in range(int(input())):
    arr.append(int(input()))
arr.sort()
max_value = -2 ** 62 -1
start = arr[0]
cnt = 1
for i in range(1 ,len(arr)):
    if start == arr[i]:
        cnt += 1
        start = arr[i]
    else:
        if max_value < cnt:
            max_value = cnt
            answer = start
        cnt = 1
        start = arr[i]
if max_value < cnt:
    max_value = cnt
    answer = start
print(answer)