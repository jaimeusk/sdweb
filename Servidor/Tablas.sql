
CREATE TABLE Eventos(
    idEvento SERIAL PRIMARY KEY,
    Artista VARCHAR(255) NOT NULL,
    Fecha VARCHAR(255) NOT NULL,
    Lugar VARCHAR(255) NOT NULL,
    Ciudad VARCHAR(255) NOT NULL,
    NumEntradas INTEGER NOT NULL 
);

CREATE TABLE Compras(
    idCompra SERIAL PRIMARY KEY,
    idEvento INTEGER REFERENCES Eventos(idEvento),
    Nombre VARCHAR(255) NOT NULL,
    Dni VARCHAR(255) NOT NULL,
    Cantidad INTEGER NOT NULL
);

\copy Eventos FROM eventos.txt
\copy Compras FROM compras.txt