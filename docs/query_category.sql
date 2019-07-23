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



-- recursive print
with recursive sub_category(no, name, parent_no, level, path, cycle) as (
	-- non-recursive term
	select c.no, c.name, c.parent_no, 0, array[c.no], false
	from category c
	where parent_no is null
	
	union all
	
	-- recursive term
	select c.no, c.name, c.parent_no, level + 1, path || c.no, c.no = any(path)
	from category c, sub_category sb
	where c.parent_no = sb.no and not cycle	
)
select no, parent_no, lpad(' ', level) || name, level, path
from sub_category
order by path;