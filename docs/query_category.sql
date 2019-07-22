-- category
select * from category order by no asc;



-- insert
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
values(nextval('seq_category_no'), '카테고리3', null);



-- getAllParents
select *
from category
where parent_no is null
order by no asc;



-- getChildren
with recursive sub_category as (
	-- non-recursive term
	select *
	from category
	where no = 3
	
	union all
	
	-- recursive term
	select c.*
	from category as c
	join sub_category as sc
	on (c.parent_no = sc.no)
)
select * from sub_category order by no;



-- update
update category
set
name = '카테고리1-1-1',
parent_no = 3
where no = 5;



-- delete
delete
from category
where no = 1;
