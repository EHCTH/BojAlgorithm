dict = {3: ['A', 'B', 'C'], 4: ['D', 'E', 'F'], 5: ['G', 'H', 'I'], 6: ['J', 'K', 'L'], 7: ['M', 'N', 'O'],
        8: ['P', 'Q', 'R', 'S'], 9: ['T', 'U', 'V'], 10: ['W', 'X', 'Y', 'Z']}
answer = 0
word = input().upper()
for wo in word:
    for i, values in dict.items():
        if wo in values:
            answer += i
print(answer)