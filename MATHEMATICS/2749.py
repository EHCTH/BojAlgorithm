mod = 1000000
p = mod // 10 * 15
n = int(input())
fibo_list = [0, 1] 
for _ in range(2, p):
    fibo_list.append(fibo_list[-1] + fibo_list[-2])
    fibo_list[-1] %= mod
    
print(fibo_list[n % p])