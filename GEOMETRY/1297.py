d, h, w = map(int, input().split())
ret = d / (h ** 2 + w ** 2) ** (1/2)
print(int(h * ret), int(w * ret))