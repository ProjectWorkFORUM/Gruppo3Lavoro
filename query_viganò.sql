CREATE TABLE esperienza (   
id_esperienza int PRIMARY KEY,   
titolo varchar(100) NOT NULL,   
descrizione varchar(100) NOT NULL,   
prezzo int NOT NULL 
)


CREATE TABLE acquisto (
  id_acquisto int PRIMARY KEY,
  data_acquisto date NOT NULL,
	id_utente int,
	id_viaggio int,
	FOREIGN KEY (id_utente) REFERENCES utenti(id),
   FOREIGN KEY (id_viaggio) REFERENCES viaggi(id)
);

CREATE TABLE vaggio_esperienza (
    id int NOT NULL AUTO_INCREMENT,
    id_esperienza int NOT NULL,
    id_viaggio int NOT NULL,
    PRIMARY KEY (id),
    KEY id_esperienza (id_esperienza),
    KEY id_viaggio (id_viaggio),
    CONSTRAINT vaggio_esperienza_ibfk_1 FOREIGN KEY (id_esperienza) REFERENCES esperienza (id_esperienza),
    CONSTRAINT vaggio_esperienza_ibfk_2 FOREIGN KEY (id_viaggio) REFERENCES viaggi (id)
)