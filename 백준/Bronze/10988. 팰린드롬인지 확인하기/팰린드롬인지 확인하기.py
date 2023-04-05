def is_palindrome(word):
    return word == word[::-1]

word = input()
print(int(is_palindrome(word)))