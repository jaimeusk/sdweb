#!/bin/bash
cp Servidor/Entrada.class Cliente/Entrada.class
cp Servidor/Evento.class Cliente/Evento.class
cp Servidor/ServicioEntradas.class Cliente/ServicioEntradas.class
cp Servidor/ServicioEventos.class Cliente/ServicioEventos.class
cp Servidor/FabricaServicioEvento.class Cliente/FabricaServicioEvento.class
cd Cliente
javac *.java
java -Djava.security.policy=cliente.permisos -Djava.net.preferIPv4Stack=true ClienteEntradas 127.0.0.1 51000 54321
