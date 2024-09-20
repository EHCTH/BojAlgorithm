N = (int(input())//100)*100
F = int(input())

for i in range(100):
    if N % F == 0:
        N = str(N)
        break
    N += 1
print(N[-2:])