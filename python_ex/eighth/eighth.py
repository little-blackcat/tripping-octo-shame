import png, array  

reader = png.Reader(filename='oxygen.png')  
w, h, pixels, metadata = reader.asRGBA()  

pixelsAsList = list(pixels)

l = (pixelsAsList[47])[18::28]
for i in l:
	print chr(i),

	
