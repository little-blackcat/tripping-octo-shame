#!/bin/bash

#$1 - zadane drzewo katalogow

if [ $# != 1 ]
then
	echo "Zla liczba parametrow!"
	exit 1;
fi

find $1 -type f | awk '/a+[0-9]+$/ { print }';

