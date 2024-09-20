list = sorted(list(input()), reverse=True)
if list[-1] != '0' :
    print(-1)
else:
    sum = 0
    for i in list:
        sum += int(i)
    if sum % 3 != 0 :
        print(-1)
    else :
        print(''.join(list))