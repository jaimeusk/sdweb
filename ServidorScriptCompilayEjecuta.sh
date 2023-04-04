#!/bin/bash
cd Servidor
rmiregistry -J-Djava.net.preferIPv4Stack=true 51000 &
rmiregistry 54321 &
javac *.java
java -Djava.security.policy=servidor.permisos ServidorEntradas 51000 &
#java -Djava.security.policy=servidor.permisos ServidorEventos 54321