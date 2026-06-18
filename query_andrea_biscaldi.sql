QUERY ANDREA BISCALDI

CREATE TABLE `utenti` (
    `id` int NOT NULL AUTO_INCREMENT,
    `username` varchar(15) NOT NULL,
    `nome` varchar(20) NOT NULL,
    `cognome` varchar(20) NOT NULL,
    `email` varchar(50) NOT NULL,
    `password` varchar(12) NOT NULL,
    `data_nascita` date NOT NULL,
    `data_registrazione` date NOT NULL,
    `stato_account` tinyint(1) NOT NULL DEFAULT '1',
    PRIMARY KEY (`id`)
)

CREATE TABLE immagine(  
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT ,
    url varchar(255) NOT NULL,
    data_caricamento datetime NOT NULL,
    id_utente int NOT NULL,
    id_recensione int NOT NULL,
    FOREIGN KEY (id_utente) REFERENCES utenti(id),
    FOREIGN KEY (id_recensione) REFERENCES recensione(id)
);

CREATE TABLE `moderazione` (
    `id` int NOT NULL AUTO_INCREMENT,
    `tipo_azione` varchar(20) NOT NULL,
    `motivo` varchar(255) NOT NULL,
    `data_azione` datetime NOT NULL,
    `data_scadenza` datetime DEFAULT NULL,
    `id_utente` int NOT NULL,
    `id_admin` int NOT NULL,
    PRIMARY KEY (`id`),
    KEY `id_utente` (`id_utente`),
    KEY `id_admin` (`id_admin`),
    CONSTRAINT `moderazione_ibfk_1` FOREIGN KEY (`id_utente`) REFERENCES `utenti` (`id`),
    CONSTRAINT `moderazione_ibfk_2` FOREIGN KEY (`id_admin`) REFERENCES `utenti` (`id`)
)
