for i in range(int(input())):
    h, w, n = map(int, input().split())
    num = n//h + 1
    floor = n % h
    if n % h == 0:
        num = n//h
        floor = h
    print(f'{floor*100+num}')