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
		
select * from product;
select * from product_option_item;