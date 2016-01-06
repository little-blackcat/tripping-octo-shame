import random
import csv
import sys

names = ['Anna', 'Aleksanda', 'Katarzyna', 'Klaudia', 'Joanna', 'Natalia', 'Magdalena', 'Alicja', 'Martyna', 'Daria', 'Paulina',
         'Michał', 'Krzysztof', 'Jakub', 'Aleksander', 'Sebastian', 'Marcin', 'Dominik', 'Mateusz', 'Piotr', 'Adam', 'Konrad']

surnames = ['Nowak', 'Maksymowicz', 'Błaszczyk', 'Kowalczyk', 'Abarowicz', 'Abaszyn', 'Abelec', 'Babko', 'Bachara', 'Baczak', 'Kabela', 'Kacera',
            'Kachel', 'Kacprzak', 'Kaczanka', 'Kaczmarek', 'Kaczmarzyk', 'Kadaś', 'Kadula', 'Kaduszko', 'Deszczowiec', 'Brzoza', 'Jabłko',
            'Ołówek', 'Trawa', 'Sosna', 'Wróbel', 'Struś', 'Jaskółka', 'Tygrys', 'Rumianek', 'Gęś', 'Kaczka', 'Róża']

def getPesel():
    peselYear = random.randint(0,99)
    peselMonth = random.randint(1,12)
    peselDay = random.randint(1,28)
    peselRest = random.randint(10000, 99999)
    pesel = '{0:02d}{1:02d}{2:02d}{3}'.format(peselYear, peselMonth, peselDay, peselRest)
    return pesel

csv.register_dialect('semicolon', delimiter=';')

f = open('myData.csv', 'wt')

writer = csv.writer(f, dialect='semicolon')
writer.writerow(('PESEL', 'Name', 'Surname'))

i = 0
while i < 7400:
    writer.writerow((getPesel(), (random.choice(names)), random.choice(surnames)))
    i += 1
f.close()



