use CarRentService

-- select * from car;

delete from borrowed_date
delete from customer_cars
delete from car
delete from customer_role
delete from customer

drop table borrowed_date
drop table customer_date
drop table car
drop table customer

update car set id = 0 where id = 1;
update car set id = 1 where id = 2;
update customer set customer_id = 0 where customer_id = 1;

insert into car (id, description, name, price)
values (0, 'auto', 'auto2', 100)
insert into car (id, description, name, price)
values (1, 'auto', 'auto3', 100)

select * from car 
select * from borrowed_date
select * from customer_cars
select * from customer

select bd.car_id from borrowed_date bd where bd.customer_id = 113
 