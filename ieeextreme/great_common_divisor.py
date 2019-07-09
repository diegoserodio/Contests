divisors = []

def get_number():
	data = list(input().split(' '))
	a = int(data[0])
	b = int(data[1])
	return a, b

def common_divisor():
	global divisors
	a, b = get_number()
	max_value = max(a, b)
	for i in range(1, max_value+1):
		if a % i == 0 and b % i == 0:
			divisors.append(i)
	max_divisor = max(divisors)
	print(max_divisor)
	print("\n")

while True:
	common_divisor()
