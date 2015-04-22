#!/bin/bash

#$1 - nazwa pliku

if [ $# != 1 ]
then
	echo "Zla liczba parametrow!"
	exit 1;
fi

if [ -f $1 ]
then
	awk 'BEGIN { RS= "[.?!]"} { $1=$1 } { gsub(/\n/, " ") } { if (NF > 1) { print $1 " " $2 " " $3  "" RT } }' $1;
fi
