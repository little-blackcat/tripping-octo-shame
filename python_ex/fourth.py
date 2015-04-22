f = open("fourth.txt", "r+")

line = f.read(9)
counter = 0

while counter < 25:
	if ((line[0]).isupper() != True) and ((line[1]).isupper() == True) and ((line[2]).isupper() == True) and ((line[3]).isupper() == True) and ((line[4]).isupper() == False) and ((line[5]).isupper() == True) and ((line[6]).isupper() == True) and ((line[7]).isupper() == True) and ((line[8]).isupper() != True):
		print line
		f.seek(-8,1)
		line = f.read(9)
		counter += 1
	else:
		f.seek(-8,1)
		line = f.read(9)