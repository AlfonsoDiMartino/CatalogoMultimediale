#!/bin/bash
binpath=Git/PSSS/bin

if [ $# -ne 1 ]; then
	echo "Numero di parametri errato!";
	echo "Uso: startup.sh (server | utente | amministratore)";
	exit 0;
fi

case "$1" in
server)	echo "java -jar server.jar -classpath $HOME/$binpath -Djava.rmi.server.codebase=file:$HOME/$binpath";
	java -jar server_mysql.jar -classpath $HOME/$binpath -Djava.rmi.server.codebase=file:$HOME/$binpath;
	;;

utente) echo "java -jar utente.jar -classpath $HOME/$binpath ";
	java -jar utente.jar -classpath $HOME/$binpath;
	;;

amministratore) echo "java -jar amministratore.jar -classpath $HOME/$binpath";
	java -jar amministratore.jar -classpath $HOME/$binpath;
	;;
*) 	echo "Errore!!";
	echo "Uso: startup.sh (server | utente | amministratore)";
esac;

