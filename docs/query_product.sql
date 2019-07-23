-- product
select * from product;
select * from product_option_name;
select * from product_option_value;
select * from product_option_item;
select * from product_category;
select * from product_image;




-- product.insert
-- 1. product.insert
-- 2. product_option_name.insert(after all delete)
-- 3. product_option_value.insert(after all delete)
-- 4. product_option_item.insert(after all delete)
-- 5. product_category.insert
-- 6. product_image.insert
insert
into product(no)
values(
	nextval('seq_product_no'),
);
