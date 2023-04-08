#!/bin/bash
pkill rmiregistry
cd Servidor
rmiregistry 54321 &
javac *.java
java -Djava.security.policy=servidor.permisos ServidorEventos 54321