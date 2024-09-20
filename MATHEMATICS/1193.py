n = int(input())
line = 0
end = 0
while n > end:
    line += 1
    end += line
tmp = end - n
if line % 2 == 0:
    top = line - tmp
    bottom = tmp + 1
else:
    top = tmp + 1
    bottom = line - tmp

print(f"{top}/{bottom}")