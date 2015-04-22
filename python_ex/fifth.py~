import urllib2

number = ""
url = 'http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=12345'

for i in range (0, 399):
	site_text = urllib2.urlopen(url).read()
	print site_text
	for i in site_text:
		if i.isdigit() == True:
			number += i;
	if site_text == "Yes. Divide by two and keep going.":
		url = "http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=" + '(16044/2)'
	else:
		url = "http://www.pythonchallenge.com/pc/def/linkedlist.php?nothing=" + number
	print url	
	number = ""
	f = urllib2.urlopen(url)
	

			
	