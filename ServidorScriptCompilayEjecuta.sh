#!/bin/bash
cd Servidor
rmiregistry -J-Djava.net.preferIPv4Stack=true 50000 &
javac *.java
java -Djava.security.policy=servidor.permisos ServidorEntradas 50000