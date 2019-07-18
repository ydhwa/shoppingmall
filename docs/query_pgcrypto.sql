-- use pgcrypto(슈퍼 유저(postgres)로 실행)
create extension pgcrypto;



-- encrypt test(aes)
select encode(encrypt(convert_to('', 'utf-8'), 'key', 'AES'), 'hex');
select convert_from(decrypt(decode('', 'hex'), 'key', 'AES'), 'utf-8');
select encode(encrypt(convert_to('ydhwa_18@naver.com', 'utf-8'), 'key', 'AES'), 'hex');
select convert_from(decrypt(decode('43235bf2a700d8e1a360a86321a9d35c1ac857924b156ed5ba2ac46132cf1637', 'hex'), 'key', 'AES'), 'utf-8');



-- encrypt test(sha)
select encode(digest('', 'sha512'), 'hex');
select encode(digest('asdf1234!', 'SHA512'), 'hex');
