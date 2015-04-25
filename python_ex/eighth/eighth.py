import png, array  
next_level = ""
tmp = 0

reader = png.Reader(filename='oxygen.png')  
w, h, pixels, metadata = reader.asRGBA()  

pixelsAsList = list(pixels)

l = (pixelsAsList[47])[18::28]
for i in l:
	print chr(i),

	