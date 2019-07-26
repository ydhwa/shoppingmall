-- bucket
select * from bucket_item;

/*
WITH upsert AS (
	update bucket_item
	set quantity = quantity + 1
	where member_no = 1
	returning *
)
insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(1, null, 1, 1, now())
where not exists (select * from upsert);
*/

-- insert if exist update
-- member
insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(1, null, 3, 1, now())
on conflict(member_no, product_option_item_no) do 
update
set quantity = bucket_item.quantity + excluded.quantity;
-- nonmember
insert into bucket_item(member_no, identifier, product_option_item_no, quantity, reg_date)
values(null, '19072520022353484b4fc3b-115a-43b4-a57b-fdd9e48aa2ef', 2, 4, now())
on conflict(identifier, product_option_item_no) do 
update
set quantity = excluded.quantity;


-- 만료일자 계산 연습
select date(now()) + interval '7' day;