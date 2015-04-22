#!/bin/bash

#$1 - zadane drzewo katalogów

if [ $# != 1 ]
then
	echo "Zla liczba parametrow!"
	exit 1;
fi

find $1 -type f -size -100c \( ! -perm +300 )\ \( -perm -030 -o -perm -003 \) -print
