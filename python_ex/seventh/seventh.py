import zipfile

zf = zipfile.ZipFile('channel.zip', 'r')

number = ""
file_name = "90052.txt"


for i in range (0, 908):
	file_text = open(file_name).read()
	for i in file_text:
		if i.isdigit() == True:
			number += i;
	file_name = number + ".txt"
	info = zf.getinfo(file_name)
	print info.comment,
	number = ""


