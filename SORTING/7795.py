for T in range(int(input())):
    arr1, arr2 = [], []
    a, b = map(int, input().split())
    arr1 = list(map(int, input().split()))
    arr2 = list(map(int, input().split()))
    arr1.sort()
    arr2.sort()
    answer = 0
    for i in range(len(arr1)):
        for j in range(len(arr2)):
            if arr1[i] > arr2[j]:
                answer += 1
            else:
                break
    print(answer)