import sys
array = [0] * 2000001
for T in range(int(sys.stdin.readline())):
    array[int(sys.stdin.readline()) + 1000000] += 1
for i in range(2000001):
    while array[i] != 0:
        array[i] -= 1
        print(i - 1000000)