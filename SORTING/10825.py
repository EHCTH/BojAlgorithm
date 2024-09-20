arr = []
# 0, 1, 2 국 영 수
for T in range(int(input())):
    arr.append(input().split())
for i in sorted(arr, key= lambda x: (-int(x[1]), int(x[2]), -int(x[3]), x[0])):
    print(i[0])