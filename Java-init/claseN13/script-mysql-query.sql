insert into departament(name,current_budget) values('logistica',34000002);
insert into departament (name,current_budget) values('sistemas',2132131);
insert into departament (name,current_budget) values('compras',400032133);

insert into nationality (name) values('Argentina'),('chile'),('uruguay'),('mexico');

insert into employees values(40043024,'kevin','De jesus',1,2);
insert into employees values(21031932,'Esteban','Juarez',4,1);

update employess set nationality_id=3 where dni=21031932;

create view employees_logistics as 
select first_name as FirstName,last_name as LastName,d.name as Departament from employees as e
join departament as d where d.id= e.departament_id and d.name='logistica';

delete  from departament where name='logistica';

select * from employees_logistics;
select * from departament order by name asc;