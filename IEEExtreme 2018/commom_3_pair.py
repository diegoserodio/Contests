parity = False
done = False
a = 0
b = []
pairs = 0

def get_numbers():
	global parity, done, a, b
	if parity == False:
		a = int(input())
		parity = True
	else:
		b = list(input().split(' '))
		parity = False
		done = True
	return a, b 
while True:
	index, numbers = get_numbers()
	if done == True:
		for i in range(index):
			for j in range(i + 1, index):
				soma = int(numbers[i])+int(numbers[j])
				if soma % 3 == 0:
					pairs = pairs + 1
		print(pairs)
		print("\n")
		pairs = 0
		done = False