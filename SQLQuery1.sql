use CarRentService

select * from car;

delete from borrowed_date
delete from customer

update car set id = 0 where id = 1;
update car set id = 1 where id = 2;

update customer set customer_id = 0 where customer_id = 1;

select * from car 
select * from borrowed_date
select * from customer


select b.borrowed_date_id
from borrowed_date b
where '1800-01-01' not between b.start_date and b.end_date
	and '3000-01-01' not between b.start_date and b.end_date
	and b.car_id = 0
	and b.car_id NOT IN (
    select DISTINCT bd.car_id
    from borrowed_date bd
    where '2021-12-02' between bd.start_date and bd.end_date
		OR '2021-12-04' between bd.start_date and bd.end_date)
group by b.borrowed_date_id
union
select id from car
where id not in (select bd.car_id from borrowed_date bd)

 