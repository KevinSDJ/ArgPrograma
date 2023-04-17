create table if not exists departament (
   id bigint auto_increment unique not null,
   name varchar(46) unique not null,
   current_budget bigint,
   primary key (id)
   )engine= InnoDB;

create table if not exists nationality (
    id bigint auto_increment unique not null,
    name varchar(36) unique not null,
    primary key (id)
    ) engine= InnoDB;

create table if not exists employees (
    dni bigint unique not null,
    first_name varchar(46) not null,
    last_name varchar(46) not null,
    nationality_id bigint unique,
    departament_id bigint unique,
    primary key (dni),
    foreign key (nationality_id) references nationality(id) on delete cascade,
    foreign key (departament_id) references departament(id) on delete cascade
    )engine =InnoDB;