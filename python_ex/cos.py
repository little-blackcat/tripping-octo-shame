from string import maketrans 

A = "map.html"
B = ""

for literka in A:
  if literka == " ":
    B = B + " "
  elif literka == "y":
    B = B + "a"
  elif literka == "(":
    B = B + "("
  elif literka == ")":
    B = B + ")"
  elif literka == ".":
    B = B + "."
  elif literka == "z":
    B = B + "b"
  else:
    B = B + chr(ord(literka) + 2)
  
print B

intab = "abcdefghijklmnopqrstuwxyz"
outtab = "cdefghijklmnopqrstuwxyzab"
trantab = maketrans(intab, outtab)

print A.translate(trantab);