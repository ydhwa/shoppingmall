-- use pgcrypto(슈퍼 유저(postgres)로 실행)
create extension pgcrypto;



-- member
select * from member;
select * from authority;
select currval('seq_member_no');


-- member.insert
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


-- member.getByUsername
select username
from member
where username = 'userA01';


-- member.getByUsernameAndPassword
select no, username, convert_from(decrypt(decode(name, 'hex'), 'key', 'AES'), 'utf-8')
from member
where
	username = 'userA01' and
	password = encode(digest('asdf1234!', 'sha512'), 'hex');




-- encrypt test(aes)
select encode(encrypt(convert_to('', 'utf-8'), 'key', 'AES'), 'hex');
select convert_from(decrypt(decode('', 'hex'), 'key', 'AES'), 'utf-8');
select encode(encrypt(convert_to('ydhwa_18@naver.com', 'utf-8'), 'key', 'AES'), 'hex');
select convert_from(decrypt(decode('43235bf2a700d8e1a360a86321a9d35c1ac857924b156ed5ba2ac46132cf1637', 'hex'), 'key', 'AES'), 'utf-8');

-- encrypt test(sha)
select encode(digest('', 'sha512'), 'hex');
select encode(digest('asdf1234!', 'SHA512'), 'hex');
