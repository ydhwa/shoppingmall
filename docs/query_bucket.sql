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



select * from bucket_item;


-- 장바구니 조회 쿼리
select
	bi.quantity as quantity,
	(bi.quantity * (p.sell_price + poi.additional_amount)) as sell_price,
	p.no as product_no,
	p.name as product_name,
	poi.no as product_option_item_no,
	poi.details as product_option_details,
	bi.member_no as member_no,
	bi.identifier as identifier
from bucket_item bi, product_option_item poi, product p
where bi.product_option_item_no = poi.no
	and p.no = poi.no
	and bi.member_no = 1
order by p.no asc;