-- Dati fittizi per il test (eseguito a ogni avvio; INSERT IGNORE evita i duplicati)

-- Alcune tabelle erano state create con la PK 'id' senza AUTO_INCREMENT: gli INSERT
-- di Hibernate (che non passano l'id) fallivano con "Field 'id' doesn't have a default value".
-- Queste ALTER sistemano lo schema a ogni avvio; se l'AUTO_INCREMENT c'e' gia' non hanno effetto.
-- Le colonne 'id' sono referenziate da foreign key: disattiviamo temporaneamente i
-- controlli FK per poterle modificare, poi li riattiviamo.
SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE recensioni MODIFY id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE acquisto   MODIFY id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE thread     MODIFY id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE risposte   MODIFY id BIGINT NOT NULL AUTO_INCREMENT;
SET FOREIGN_KEY_CHECKS = 1;

INSERT IGNORE INTO categoria (id, nome) VALUES
    (1, 'Al mare'),
    (2, 'Food e Wine'),
    (3, 'City tours'),
    (4, 'Natura'),
    (5, 'Sport');

INSERT IGNORE INTO esperienze (id, titolo, descrizione, prezzo, id_categoria) VALUES
    (1, 'Tour grotte in barca', 'Tour in barca nelle grotte di Polignano a Mare', 25, 1),
    (2, 'Cooking class',        'Prepara orecchiette con una nonna pugliese',     45, 2),
    (3, 'Matera e Alberobello', 'Giornata tra i Sassi e i trulli patrimonio Unesco', 60, 3),
    (4, 'Kayak al tramonto',    'Escursione in kayak lungo la costa di Monopoli', 35, 5),
    (5, 'Degustazione vini',    'Primitivo e Negroamaro in una masseria salentina', 30, 2),
    (6, 'Trekking nel Gargano', 'Camminata nella Foresta Umbra con guida locale', 20, 4);
