CREATE TABLE Mesa(
    id INTEGER,
    numero VARCHAR(50) NOT NULL,
    CONSTRAINT mesa_id_PK PRIMARY KEY (id),
    CONSTRAINT MESA_NUMERO_UQ UNIQUE (numero)
);

CREATE TABLE Pedido (
	id INTEGER,
	cliente VARCHAR(100) NOT NULL,
	estado VARCHAR(20) NOT NULL,
	id_mesa INTEGER NOT NULL,
	CONSTRAINT Pedido_id_PK PRIMARY KEY (id),
	CONSTRAINT Pedido_id_mesa_FK FOREIGN KEY (id_mesa) REFERENCES Mesa (id)
);

create table Adicional(
	id integer not null,
	nombre varchar(100) not null,
	precio integer not null,
	constraint adicional_pk primary key (id)
);

create table PedidoAdicional (
    id_pedido integer,
    id_adicional integer,
    CONSTRAINT PedidoAdicional_PK PRIMARY KEY (id_pedido, id_adicional),
    CONSTRAINT PedidoAdicional_Pedido_FK FOREIGN KEY (id_pedido) REFERENCES Pedido (id),
    CONSTRAINT PedidoAdicional_Adicional_FK FOREIGN KEY (id_adicional) REFERENCES Adicional (id)
);

CREATE table OpcionSopa (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE OpcionPrincipio (
    id integer PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE OpcionCarne (
    id integer NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE OpcionEnsalada (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE table OpcionJugo (
    id INTEGER PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE OpcionPedido (
    id_pedido integer not null PRIMARY KEY,
    precio integer not null,
    tipo varchar(10) not null,
    id_sopa integer,
    id_carne integer not null,
    id_principio integer not null,
    id_ensalada integer,
    id_jugo integer not null,
    CONSTRAINT OpcionPedido_pedido_FK FOREIGN KEY (id_pedido) REFERENCES Pedido (id),
    CONSTRAINT OpcionPedido_id_sopa_FK FOREIGN KEY (id_sopa) REFERENCES OpcionSopa (id),
    CONSTRAINT OpcionPedido_id_carne_FK FOREIGN KEY (id_carne) REFERENCES OpcionCarne (id),
    CONSTRAINT OpcionPedido_id_principio_FK FOREIGN KEY (id_principio) REFERENCES OpcionPrincipio (id),
    CONSTRAINT OpcionPedido_id_jugo_FK FOREIGN KEY (id_jugo) REFERENCES OpcionJugo (id),
    CONSTRAINT OpcionPedido_id_ensalada_FK FOREIGN KEY (id_ensalada) REFERENCES OpcionEnsalada (id)
);