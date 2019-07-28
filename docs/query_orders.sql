-- orders
select * from orders;
select * from orders_item;
select * from product_option_item;


-- order code
select concat('P', to_char(now(), 'YYMMDD'), '-', lpad(to_hex(13)::varchar, 4, '0'));


-- order 추가 작업
-- 1. orders.insert
-- 2. orders_items.insert
-- 3. orders.update (total_order_account)
insert into orders(no, code, date, memo, status, 
	orderer_name, orderer_home_number, orderer_phone_number, orderer_email, orderer_postal_code, orderer_base_address, orderer_detail_address,
	receiver_name, receiver_home_number, receiver_phone_number, receiver_postal_code, receiver_base_address, receiver_detail_address,
	total_order_account, member_status, member_no, password)
values(
	nextval('seq_orders_no'),
	concat('P', to_char(now(), 'YYMMDD'), '-', lpad(to_hex(currval('seq_orders_no'))::varchar, 4, '0')),
	now(),
	'주문1: 안전하게 배송해주세요~',
	'ORDER_COMPLETE',
	'주문1-주문자',
	null,
	'111-1111-1111',
	'1@1.com',
	'11111',
	'주문1-주문자-기본주소',
	'주문1-주문자-상세주소',
	'주문1-수령자',
	null,
	'111-1111-1112',
	'11112',
	'주문1-수령자-기본주소',
	'주문1-수령자-상세주소',
	null,
	'Y',
	2,
	null
);

insert into orders_item(quantity, product_price, option_contents, product_no, order_no, product_option_item_no)
values(
	1,
	(select (p.sell_price + poi.additional_amount) * 1
	 from product p, product_option_item poi
	 where
	 	p.no = poi.product_no
 	 	and poi.no = 1),
	(select details
	 from product_option_item
	 where no = 1),
	(select product_no
	 from product_option_item
	 where no = 1),
	currval('seq_orders_no'),
	1
);
update orders
set total_order_account = (
	select sum(oi.product_price)
	from orders_item oi
	where oi.order_no = currval('seq_orders_no')
)
where no = currval('seq_orders_no');



-- update some data after order
update product_option_item
set stock_quantity = 
	case
		when stock_quantity is not null then
			stock_quantity - 1
	end
where no = 1;
			
update product
set stock_quantity = 
	case
		when stock_quantity is not null then
			stock_quantity - 1
	end
where no = (
	select poi.no
	from product p, product_option_item poi
	where p.no = poi.product_no
		and poi.no = 1);
		

-- customer order(member)
select * from orders_item;
select distinct o.no as orders_no, o.code as orders_code, to_char(o.date, 'YYYY-MM-DD HH24:mm:ss') as orders_date, o.status as orders_status, o.total_order_account as orders_total_order_account,
	concat(
		(select p1.name from product p1, orders_item oi1 where p1.no = oi1.product_no and oi1.order_no = o.no order by p1.no limit 1), 
		' 외 ', 
		(select count(oi2.no) - 1 from orders o2, orders_item oi2 where o2.no = oi2.order_no and oi2.order_no = o.no), 
		'건') as details
from orders o, orders_item oi
where o.member_no = 2
	and o.no = oi.order_no
order by orders_date desc
offset '0'::integer limit '5'::integer;

select no, code, date, memo, status, orderer_name, orderer_home_number, orderer_phone_number, orderer_email, orderer_postal_code, orderer_base_address, orderer_detail_address, receiver_name, receiver_home_number, receiver_phone_number, receiver_postal_code, receiver_base_address, receiver_detail_address, total_order_account
from orders
where no = 1
	and member_no = 2;

select poi.no as no, 
	oi.quantity as quantity, 
	p.no as product_no, 
	p.code as product_code, 
	p.name as product_name, 
	poi.no as product_option_item_no, 
	poi.details as product_option_item_details,
	(p.sell_price + poi.additional_amount) * oi.quantity as sell_price
from orders o, product p, product_option_item poi, orders_item oi
where o.no = oi.order_no
	and oi.product_option_item_no = poi.no
	and poi.product_no = p.no
	and o.no = 1;

-- customer order(non-member)
select *
from orders
where no = 2
	and password = encode(digest('asdf1234!', 'sha512'), 'hex');

-- customer update order's status (-> CANCEL)
update orders
set status = 'CANCEL'
where no = 3;


-- admin order
select *
from orders
where code like concat('%', '0726','%')
	and date > date('2019-07-21')
	and memo like concat('%', '','%')
	and orderer_name like concat('%', '','%')
	and orderer_home_number like concat('%', '','%')
	and orderer_phone_number like concat('%', '','%')
	and orderer_email like concat('%', '','%')
	and orderer_postal_code like concat('%', '','%')
	and orderer_base_address like concat('%', '','%')
	and orderer_detail_address like concat('%', '','%')
	and receiver_name like concat('%', '','%')
	and receiver_home_number like concat('%', '','%')
	and receiver_phone_number like concat('%', '','%')
	and receiver_postal_code like concat('%', '','%')
	and receiver_base_address like concat('%', '','%')
	and receiver_detail_address like concat('%', '','%')
	and member_status = 'Y'	
	and member_no = 2
order by date desc
offset '0'::integer limit '5'::integer;

select * 
from orders 
where no = 1;

-- admin update order's status (-> SHIPMENT_COMPLEMTE and etc.)
update orders
set status = 'SHIPMENT_COMPLETE'
where no = 3;
