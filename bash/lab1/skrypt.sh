#!/bin/bash

for files in $1
do
rm *.old -f
done

for file in $1
do
	if [ -w $file ]
	then
		mv $file $file.old
	fi
done
