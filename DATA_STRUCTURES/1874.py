def check_sequence(n, sequence):
    stack = [] 
    result = []  
    current_number = 1

    for num in sequence:
        while current_number <= num:
            stack.append(current_number)
            result.append('+')
            current_number += 1

        if stack[-1] == num:  
            stack.pop()
            result.append('-')
        else:
            return "NO"  

    return "\n".join(result)  

n = int(input())  
sequence = [int(input()) for _ in range(n)]  

result = check_sequence(n, sequence)
print(result)