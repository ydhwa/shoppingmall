-- sequence와 새로운 type을 사전에 생성해둬야 한다.
-- define_sequence_w3-1.sql
-- define_enum_w4-1.sql

-- member & authority
select * from member;
select * from authority;

insert 
into member
values(
	nextval('seq_member_no'),
	'userA01',
	encode(digest('asdf1234!', 'sha512'), 'hex'),
	now(),
	encode(encrypt(convert_to('회원A-1', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('1980-01-01', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('111-111-1111', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('111-1111-1111', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('userA01@test.com', 'utf-8'), 'key', 'AES'), 'hex'),
	'ENABLE',
	'N'
);
insert
into authority
values(currval('seq_member_no'), 'ROLE_USER');

insert 
into member
values(
	nextval('seq_member_no'),
	'userA02',
	encode(digest('asdf1234!', 'sha512'), 'hex'),
	now(),
	encode(encrypt(convert_to('회원A-2', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('1970-01-01', 'utf-8'), 'key', 'AES'), 'hex'),
	null,
	encode(encrypt(convert_to('222-2222-2222', 'utf-8'), 'key', 'AES'), 'hex'),
	encode(encrypt(convert_to('userA02@test.com', 'utf-8'), 'key', 'AES'), 'hex'),
	'ENABLE',
	'N'
);
insert
into authority
values(currval('seq_member_no'), 'ROLE_USER');



-- category
select * from category;

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), '카테고리1', null);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), '카테고리2', null);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), '카테고리1-1', 1);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), '카테고리1-2', 1);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), '카테고리1-1-1', 3);



-- shipping address
select * from shipping_address;
insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11111', '기본주소1', '상세주소1', '회원1의주소');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(2, '22222', '기본주소2', '상세주소2', '회원2의주소');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11112', '기본주소1-1', '상세주소1-1', '회원1의주소-1');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11113', '기본주소1-1-1', '상세주소1-1-1', '회원1의주소-1-1');



--------------------------------------------------------------
-- product
select * from product;
insert into product(no, code, name, supply_price, sell_price, summary_description, detailed_description, reg_date, weight, option_available, del_status, display_status, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_no'),
	concat('P', lpad(currval('seq_product_no')::varchar, 7, '0')),
	'상품1',
	1000,
	10000,
	'요약설명-1',
	'상세설명-1',
	now(),
	1.1,
	'Y',
	'N',
	'MAIN',
	'Y',
	'STOCK',
	100
);

insert into product(no, code, name, supply_price, sell_price, summary_description, detailed_description, reg_date, weight, option_available, del_status, display_status, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_no'),
	concat('P', lpad(currval('seq_product_no')::varchar, 7, '0')),
	'상품2',
	2000,
	20000,
	'요약설명-2',
	'상세설명-2',
	now(),
	2.22,
	'N',
	'N',
	'NONE',
	'Y',
	'NON_STOCK',
	200
);

insert into product(no, code, name, supply_price, sell_price, summary_description, detailed_description, reg_date, weight, option_available, del_status, display_status, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_no'),
	concat('P', lpad(currval('seq_product_no')::varchar, 7, '0')),
	'상품3',
	3000,
	30000,
	null,
	null,
	now(),
	3.3,
	'Y',
	'Y',
	'EVENT',
	'N',
	'NON_STOCK',
	0
);

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



-- product_option_name
select * from product_option_name;
insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 1, '사이즈');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 1, '색상');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 1, '길이');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 3, '사이즈');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 4, '사이즈');



-- product_option_value
select * from product_option_value;
insert into product_option_value(product_option_name_no, value)
values(1, 'S');

insert into product_option_value(product_option_name_no, value)
values(1, 'M');

insert into product_option_value(product_option_name_no, value)
values(1, 'L');

insert into product_option_value(product_option_name_no, value)
values(4, 'S');

insert into product_option_value(product_option_name_no, value)
values(4, 'M');

insert into product_option_value(product_option_name_no, value)
values(4, 'L');

insert into product_option_value(product_option_name_no, value)
values(5, 'L');

insert into product_option_value(product_option_name_no, value)
values(5, 'XL');

insert into product_option_value(product_option_name_no, value)
values(2, 'BLACK');

insert into product_option_value(product_option_name_no, value)
values(2, 'WHITE');

insert into product_option_value(product_option_name_no, value)
values(3, 'LONG');

insert into product_option_value(product_option_name_no, value)
values(3, 'SHORT');



-- product_option_item
select * from product_option_item;

insert into product_option_item(no, product_no, option_name_keys, option_value_keys, details, additional_amount, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_option_item_no'),
	1,
	'1;2;3',
	'2;2;2',
	'사이즈=M;색상=WHITE;길이=SHORT',
	1000,
	'Y',
	'STOCK',
	10
);

insert into product_option_item(no, product_no, option_name_keys, option_value_keys, details, additional_amount, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_option_item_no'),
	1,
	'1;2;3',
	'3;1;1',
	'사이즈=L;색상=BLACK;길이=LONG',
	1000,
	'Y',
	'STOCK',
	20
);

insert into product_option_item(no, product_no, option_name_keys, option_value_keys, details, additional_amount, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_option_item_no'),
	2,
	null,
	null,
	'DEFAULT',
	0,
	'Y',
	'NON_STOCK',
	200
);



-- product_image
select * from product_image;

insert into product_image(name, extension, path, status, product_no)
values('2019073022023022', 'png', '/images/', 'SUB', 1);

insert into product_image(name, extension, path, status, product_no)
values('20190723212230111', 'jpg', '/images/', 'MAIN', 1);



-- product_category
select * from product_category;

insert into product_category(product_no, category_no)
values(1, 1);

insert into product_category(product_no, category_no)
values(1, 3);

insert into product_category(product_no, category_no)
values(1, 4);

insert into product_category(product_no, category_no)
values(1, 5);

insert into product_category(product_no, category_no)
values(2, 2);

insert into product_category(product_no, category_no)
values(3, 2);

insert into product_category(product_no, category_no)
values(4, 2);



------------------------------------------------------------
-- bucket_item
select * from bucket_item;

-- insert if exist update
insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(1, null, 1, 2, now())
on conflict(member_no, product_option_item_no) do 
update
set quantity = bucket_item.quantity + coalesce(bucket_item.quantity, excluded.quantity);

insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(1, null, 3, 1, now())
on conflict(member_no, product_option_item_no) do 
update
set quantity = bucket_item.quantity + coalesce(bucket_item.quantity, excluded.quantity);


insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(null, 'asdf', 2, 4, now())
on conflict(identifier, product_option_item_no) do 
update
set quantity = bucket_item.quantity + coalesce(bucket_item.quantity, excluded.quantity);

-- update identifier => timestamp + UUID
update bucket_item
set identifier = '19072520022353484b4fc3b-115a-43b4-a57b-fdd9e48aa2ef'
where member_no is null;

------------------------------------------------------------

-- orders
-- orders_item
select * from orders;
select * from orders_item;

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
insert into orders_item(quantity, product_price, option_contents, product_no, order_no, product_option_item_no)
values(
	3,
	(select (p.sell_price + poi.additional_amount) * 3
	 from product p, product_option_item poi
	 where
	 	p.no = poi.product_no
 	 	and poi.no = 2),
	(select details
	 from product_option_item
	 where no = 2),
	(select product_no
	 from product_option_item
	 where no = 2),
	currval('seq_orders_no'),
	2
);
insert into orders_item(quantity, product_price, option_contents, product_no, order_no, product_option_item_no)
values(
	1,
	(select (p.sell_price + poi.additional_amount) * 1
	 from product p, product_option_item poi
	 where
	 	p.no = poi.product_no
 	 	and poi.no = 3),
	(select details
	 from product_option_item
	 where no = 3),
	(select product_no
	 from product_option_item
	 where no = 3),
	currval('seq_orders_no'),
	3
);
update orders
set total_order_account = (
	select sum(oi.product_price)
	from orders_item oi
	where oi.order_no = currval('seq_orders_no')
)
where no = currval('seq_orders_no');


-- 2nd order
insert into orders(no, code, date, memo, status, 
	orderer_name, orderer_home_number, orderer_phone_number, orderer_email, orderer_postal_code, orderer_base_address, orderer_detail_address,
	receiver_name, receiver_home_number, receiver_phone_number, receiver_postal_code, receiver_base_address, receiver_detail_address,
	total_order_account, member_status, member_no, password)
values(
	nextval('seq_orders_no'),
	concat('P', to_char(now(), 'YYMMDD'), '-', lpad(to_hex(currval('seq_orders_no'))::varchar, 4, '0')),
	now(),
	'주문2: 마음에 들었으면 좋겠어요!',
	'ORDER_COMPLETE',
	'주문2-주문자',
	null,
	'222-2222-2222',
	'2@2.com',
	'22222',
	'주문2-주문자-기본주소',
	'주문2-주문자-상세주소',
	'주문2-수령자',
	null,
	'222-2222-2223',
	'22223',
	'주문2-수령자-기본주소',
	'주문2-수령자-상세주소',
	null,
	'Y',
	null,
	encode(digest('asdf1234!', 'sha512'), 'hex')
);

insert into orders_item(quantity, product_price, option_contents, product_no, order_no, product_option_item_no)
values(
	1,
	(select (p.sell_price + poi.additional_amount) * 1
	 from product p, product_option_item poi
	 where
	 	p.no = poi.product_no
 	 	and poi.no = 2),
	(select details
	 from product_option_item
	 where no = 2),
	(select product_no
	 from product_option_item
	 where no = 2),
	currval('seq_orders_no'),
	2
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
	 where no = 1	),
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


-- 3rd order
insert into orders(no, code, date, memo, status, 
	orderer_name, orderer_home_number, orderer_phone_number, orderer_email, orderer_postal_code, orderer_base_address, orderer_detail_address,
	receiver_name, receiver_home_number, receiver_phone_number, receiver_postal_code, receiver_base_address, receiver_detail_address,
	total_order_account, member_status, member_no, password)
values(
	nextval('seq_orders_no'),
	concat('P', to_char(now(), 'YYMMDD'), '-', lpad(to_hex(currval('seq_orders_no'))::varchar, 4, '0')),
	now(),
	'주문3: 안전하게 배송해주세요~',
	'ORDER_COMPLETE',
	'주문3-주문자',
	'333-333-3333',
	'333-3333-3333',
	'3@3.com',
	'33333',
	'주문3-주문자-기본주소',
	'주문3-주문자-상세주소',
	'주문3-수령자',
	'333-333-3334',
	'333-3333-3334',
	'33334',
	'주문3-수령자-기본주소',
	'주문3-수령자-상세주소',
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
	 where no = 1	),
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




