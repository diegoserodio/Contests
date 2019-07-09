formated_data = (" ")

def get_number():
	global formated_data
	data = input()
	formated_data = list(data.split(' '))
	try:
		a = int(formated_data[0])
		b = int(formated_data[1])
		return a,b
	except ValueError:
		a = float(formated_data[0])
		b = float(formated_data[1])
		return a,b

a, b = get_number()

def add(a, b):
	soma = a + b
	return soma

total = add(a, b)

print(total)