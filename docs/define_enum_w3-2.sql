-- member
-- member.status
create type member_status as enum('ENABLE', 'DISABLE');
drop type member_role cascade;


-- authority
-- authority.role
create type authority_role as enum('ROLE_USER', 'ROLE_ADMIN');



-- orders
-- orders.status
create type orders_status as enum('CANCEL', 'EXCHANGE', 'RETURN', 'ORDER_COMPLETE', 'SHIPMENT_COMPLETE');



-- product
-- product.display_status
-- product.manage_status
create type product_display_status as enum('MAIN', 'EVENT', 'NONE');
create type product_manage_status as enum('STOCK', 'NON_STOCK', 'DISPLAY');



-- product image
-- product_image.status
create type product_image_status as enum('MAIN', 'SUB');