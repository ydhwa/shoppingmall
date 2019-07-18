-- member
select * from member;
select * from authority;
select currval('seq_member_no');



-- member.insert (join)
insert 
into member(no, username, password, reg_date, name, birth_date, home_number, phone_number, email, status)
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
	'ENABLE'
);
insert
into authority
values(currval('seq_member_no'), 'ROLE_USER');

insert 
into member(no, username, password, reg_date, name, birth_date, home_number, phone_number, email, status)
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
	'ENABLE'
);
insert
into authority
values(currval('seq_member_no'), 'ROLE_USER');



-- member.getByUsername (check username duplication)
select username
from member
where username = 'userA01';



-- member.getByUsernameAndPassword (login)
select no, username, convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8')
from member
where
	username = 'userA01' and
	password = encode(digest('asdf1234!', 'sha512'), 'hex');

alter table orders_item rename column option_contents to option_details;
select * from orders_item;


