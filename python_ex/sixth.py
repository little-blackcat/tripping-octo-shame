import pickle

f = open('banner.p', 'r')
mydict = pickle.load(f)
f.close()
char = ""
number = 0

for value in mydict:
	for (x, y) in value:
		for i in range(y):
			print x,
	print		