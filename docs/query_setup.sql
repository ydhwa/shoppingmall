select last_value from seq_product_no;

-- sequence�� ���ο� type�� ������ �����ص־� �Ѵ�.
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
	encode(encrypt(convert_to('ȸ��A-1', 'utf-8'), 'key', 'AES'), 'hex'),
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
	encode(encrypt(convert_to('ȸ��A-2', 'utf-8'), 'key', 'AES'), 'hex'),
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
values(nextval('seq_category_no'), 'ī�װ�1', null);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), 'ī�װ�2', null);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), 'ī�װ�1-1', 1);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), 'ī�װ�1-2', 1);

insert 
into category(no, name, parent_no)
values(nextval('seq_category_no'), 'ī�װ�1-1-1', 3);



-- shipping address
select * from shipping_address;
insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11111', '�⺻�ּ�1', '���ּ�1', 'ȸ��1���ּ�');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(2, '22222', '�⺻�ּ�2', '���ּ�2', 'ȸ��2���ּ�');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11112', '�⺻�ּ�1-1', '���ּ�1-1', 'ȸ��1���ּ�-1');

insert into shipping_address(member_no, postal_code, base_address, detail_address, description)
values(1, '11113', '�⺻�ּ�1-1-1', '���ּ�1-1-1', 'ȸ��1���ּ�-1-1');



--------------------------------------------------------------
-- product
select * from product;
insert into product(no, code, name, supply_price, sell_price, summary_description, detailed_description, reg_date, weight, option_available, del_status, display_status, availability, manage_status, stock_quantity)
values(
	nextval('seq_product_no'),
	concat('P', lpad(currval('seq_product_no')::varchar, 7, '0')),
	'��ǰ1',
	1000,
	10000,
	'��༳��-1',
	'�󼼼���-1',
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
	'��ǰ2',
	2000,
	20000,
	'��༳��-2',
	'�󼼼���-2',
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
	'��ǰ3',
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
	'��ǰ4',
	4000,
	40000,
	'��༳��-4',
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
values(nextval('seq_product_option_name_no'), 1, '������');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 1, '����');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 1, '����');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 3, '������');

insert into product_option_name(no, product_no, name)
values(nextval('seq_product_option_name_no'), 4, '������');



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
	'2;10;12',
	'������=M;����=WHITE;����=SHORT',
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
	'3;9;11',
	'������=L;����=BLACK;����=LONG',
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





------------------------------------------------------------
-- orders





-- orders_item




