#!/bin/bash

#$1 - katalog1
#$2 - katalog2

if [ $# != 2 ]
then
	echo "Zla liczba parametrow!"
	exit 1;
fi


for i in $(ls $1)
do 
	if [ -h $1/$i ]
	then
		for k in $(ls $2)
		do
			if [ -f $1/$k ] 
			then
				if [ $(readlink -f  $1/$i) = $(readlink -f  $2/$k) ]
				then
					echo "$i"
				fi
			fi 
		done
	fi
done
