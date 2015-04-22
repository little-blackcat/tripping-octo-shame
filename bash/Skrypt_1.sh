#!/bin/bash

#usuniecie plikow z rozszerzeniem .old 
rm -f $1/*.old

#dla wszystkich elementow zwroconych przez komende ls
for file in `ls $1`; do
	# dla wszystkich plikow z prawem zapisu
	if [ -f $1/$file ] && [ -w $1/$file ]; then
		# zmiana nazwy na nazwe z roszerzeniem .old
		mv $1/$file $1/$file.old
	fi
done

