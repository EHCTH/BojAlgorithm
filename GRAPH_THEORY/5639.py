import sys
sys.setrecursionlimit(10**6)
array = []
while True:
    try:
        array.append(int(input()))
    except:
        break

def solutions(left, right):
    if left > right:
        return
    root = array[left]
    next = left + 1
    while next <= right:
        if array[next] > root:
            break
        else:
            next += 1
    solutions(left + 1, next - 1) # 작은 경우
    solutions(next, right) # 큰 경우
    print(root)
solutions(0, len(array) - 1)