-- product
select * from product;
select * from product_option_name;
select * from product_option_value;
select * from product_option_item;
select * from product_category;
select * from product_image;

select last_value from seq_product_no;



-- product.insert
-- 1. product.insert
insert into product(no, code, name, supply_price, sell_price, summary_description, detailed_description, reg_date, weight, option_available, del_status, display_status, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_no'),
	concat('P', lpad(currval('seq_product_no')::varchar, 7, '0')),
	'상품4',
	4000,
	40000,
	'요약설명-4',
	null,
	now(),
	4.4,
	'Y',
	'N',
	'NONE',
	'N',
	'STOCK',
	0
);

-- 2. product_option_name.insert(after all delete)
insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 4, '사이즈');

-- 3. product_option_value.insert(after all delete)
insert into product_option_value(product_option_name_no, value)
values(3, 'SHORT');

-- 4. product_option_item.insert(after all delete)
insert into product_option_item(no, product_no, option_name_keys, option_value_keys, details, additional_amount, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_option_item_no'),
	1,
	'1;2;3',
	'2;10;12',
	'사이즈=M;색상=WHITE;길이=SHORT',
	1000,
	'Y',
	'STOCK',
	10
);

-- 5. product_category.insert
insert into product_category(product_no, category_no)
values(1, 1);

-- 6. product_image.insert
insert into product_image(name, extension, path, status, product_no)
values('2019073022023022', 'png', '/images/', 'SUB', 1);

select * from category;



----------

--- member
--- product.showSummary
select *
from product
where del_status = 'N'
	and availability = 'Y';


--- product.showDetails





----------

-- admin
--- product.showSummary
select 
	no, 
	code, 
	name, 
	sell_price, 
	to_char(reg_date, 'YYYY-MM-DD HH24:mm:ss') as reg_date, 
	del_status, 
	display_status, 
	availability, 
	manage_status,
	(select array_to_string(array_agg(name), ',')
	from category c, product_category pc
	where c.no = pc.no
		and pc.product_no = p.no) as category_list
from product p
where no = 1
	and code like concat('%', '', '%')
	and name like concat('%', '', '%')
	and sell_price > 1000
	and reg_date > '2019-07-22'
	and del_status = 'N'
	and display_status = 'MAIN'
	and availability = 'Y'
	and manage_status = 'STOCK'
	and (select array_to_string(array_agg(name), ',')
		from category c, product_category pc
		where c.no = pc.no
			and pc.product_no = p.no) like concat('%', '', '%')
order by del_status, code asc
offset '0'::integer limit '5'::integer;

-- category list(practice)
select array_to_string(array_agg(name), ',')
from category c, product_category pc
where c.no = pc.no
	and pc.product_no = 1;


--- product.showDetails
select 
	no, 
	code, 
	name, 
	supply_price,
	sell_price, 
	summary_description,
	detailed_description,
	to_char(reg_date, 'YYYY-MM-DD HH24:mm:ss') as reg_date, 
	weight,
	option_available,
	del_status, 
	display_status, 
	availability, 
	manage_status,
	stock_quantity
from product
where no = 1;

select *
from product_option_name
where product_no = 1;

select *
from product_option_value
where product_option_name_no = 1;

select *
from product_option_item
where product_no = 1;

select *
from category c, product_category pc
where c.no = pc.category_no
	and pc.product_no = 1;

select *
from product_image
where product_no = 1;



----------

--- product.update





----------

--- product.delete
update product
set del_status = 'Y'
where no = 3;
------

select * from product_option_name;
select * from product_option_value;

select pov.* 
from product_option_value pov, product_option_name pon
where pov.product_option_name_no = pon.no
	and pon.product_no = 1;

select *
from product_option_name
where product_no = 1;
