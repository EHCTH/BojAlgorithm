n = int(input())
arr = list(map(int, input().split()))
arr.sort()
k = int(input())
answer = 0
left, right = 0, len(arr) - 1
sum = 0
while left < right:
    sum = arr[left] + arr[right]
    if sum == k:
        answer += 1
    if sum < k:
        left += 1
    else:
        right -= 1
print(answer)