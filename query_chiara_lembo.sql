use projectwork;

create table viaggi(
	id int not null auto_increment,
    primary key(id),
    luogo varchar(20),
    data date
);

create table ticket(
	id int not null auto_increment,
    primary key(id),
    oggetto varchar(30),
    descrizione varchar(255),
    isChiuso boolean,
    priorità enum('bassa', 'media','alta'),
    data_apertura date,
    id_utente int,
    foreign key(id_utente) references utenti (id)
);
