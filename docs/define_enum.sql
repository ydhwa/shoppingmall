/* 프로젝트 데이터베이스에서 사용하는 enum 타입들 정의 */

-- 회원
-- member.status
-- member.role
create type member_status as enum('ENABLE', 'DISABLE');
create type member_role as enum('USER', 'ADMIN');


-- 쿠폰
-- coupon.discount_method
create type coupon_discount_method as enum('RATE', 'AMOUNT');



-- 주문
-- orders.status
create type orders_status as enum('CANCEL', 'EXCHANGE', 'RETURN', 'ORDER_COMPLETE', 'SHIPMENT_COMPLETE');



-- 배송
-- shipping.method
create type shipping_method as enum('POST_OFFICE', 'PARCEL');



-- 결제
-- payment.method
-- payment.status
create type payment_method as enum('CARD', 'DEPOSIT', 'KAKAO', 'NAVER');
create type payment_status as enum('DEPOSIT_BEFORE', 'WAIT_ADDITIONAL_DEPOSIT', 'DEPOSIT_COMPLETE', 'PAYMENT_COMPLETE')



-- 상품
-- product.display_status
-- product.manage_status
create type product_display_status as enum('MAIN, EVENT, NONE');
create type product_manage_status as enum('STOCK', 'NON_STOCK', 'DISPLAY');



-- 상품 이미지
-- product_image.status
create type product_image_status as enum('MAIN', 'SUB');