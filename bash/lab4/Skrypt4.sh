#!/bin/bash

#$1 - zadane drzewo katalogow

if [ $# != 1 ]
then
	echo "Zla liczba parametrow!"
	exit 1;
fi

find $1 -type f -printf "%f %p\n" | sort | uniq --skip-fields!=2 
