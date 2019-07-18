-- category
select * from category;

-- product
select * from product;
select * from product_option_name;
select * from product_option_value;
select * from product_category;
select * from product_image;



-- category.insert




-- product.insert
-- 1. product.insert
-- 2. product_option_name.insert
-- 3. product_option_value.insert
-- 4. product_option_item.insert
-- 5. product_category.insert
-- 6. product_image.insert
insert
into product(no)
values(
	nextval('seq_product_no'),
);
