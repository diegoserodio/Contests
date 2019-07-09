shoe_sizes = []
shoe_sides = []
sizes_checked = []
sides_checked = []
pairs = 0
index = 1

def get_data():
	global shoe_sides, shoe_sizes, index, pairs
	number = int(input())
	for i in range(number):
		data = list(input().split(' '))
		size = int(data[0])
		side = str(data[1])
		shoe_sizes.append(size)
		shoe_sides.append(side)
	for k in range(len(shoe_sizes)):
		for j in range(index, len(shoe_sizes)):
			if k not in sizes_checked and j not in sizes_checked:
				if shoe_sizes[k] == shoe_sizes[j] and shoe_sides[k] != shoe_sides[j]:
					pairs = pairs + 1
					index = k + 1
					sizes_checked.append(k)
					sizes_checked.append(j)
				else:
					index = index + 1
			else:
				index = index + 1
	print(pairs)

get_data()
