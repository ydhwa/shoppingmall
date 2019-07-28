-- member
select * from member;
select * from authority;
select currval('seq_member_no');



-- member.insert (join)
insert 
into member
values(
	nextval('seq_member_no'),
	'userA01',
	encode(digest('asdf1234!', 'sha512'), 'hex'),
	now(),
	encode(encrypt(convert_to('È¸¿øA-1', 'utf-8'), 'key', 'AES'), 'hex'),
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



-- member.select
-- membership
select * from member;
select
		no,
		username,
		reg_date,
		convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(birth_date, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(home_number, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(phone_number, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(email, 'hex'), 'key', 'AES'), 'utf-8')
from member
where no = 1
	and del_status = 'N';

-- admin
select * from member;
select
		no,
		username,
		reg_date,
		convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(birth_date, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(home_number, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(phone_number, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(email, 'hex'), 'key', 'AES'), 'utf-8'),
		status,
		del_status
from member
where no = 1;

-- admin select search list
select * from member;
select
		no,
		username,
		reg_date,
		convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(birth_date, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(phone_number, 'hex'), 'key', 'AES'), 'utf-8'),
		convert_from(decrypt(decode(email, 'hex'), 'key', 'AES'), 'utf-8')
from member
where username like concat('%', 'user', '%')
	and reg_date > date('2019-07-21')
	and convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8') like concat('%', 'È¸', '%')
	and convert_from(decrypt(decode(birth_date, 'hex'), 'key', 'AES'), 'utf-8') like '1980-01-01'
	and convert_from(decrypt(decode(home_number, 'hex'), 'key', 'AES'), 'utf-8') like concat('%', '1111', '%')
	and convert_from(decrypt(decode(phone_number, 'hex'), 'key', 'AES'), 'utf-8') like concat('%', '1111', '%')
	and convert_from(decrypt(decode(email, 'hex'), 'key', 'AES'), 'utf-8') like concat('%', 'com', '%')
offset '0'::integer limit '5'::integer;

select to_char(now(), 'YYYY-MM-DD hh:mm:ss');