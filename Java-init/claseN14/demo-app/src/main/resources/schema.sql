create table if not exists department (
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

create table if not exists employee (
    id bigint auto_increment unique not null,
    dni bigint unique not null,
    first_name varchar(46) not null,
    last_name varchar(46) not null,
    nationality_id bigint unique,
    department_id bigint unique,
    primary key (id),
    foreign key (nationality_id) references nationality(id) on delete set null on update cascade,
    foreign key (department_id) references department(id) on delete set null on update cascade
    )engine =InnoDB;