chess = [input() for _ in range(8)]
cnt = 0

for x in range(8):
    for y in range(8):
        if x % 2 == y % 2 and chess[x][y] == 'F':
            cnt += 1

print(cnt)