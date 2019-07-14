/* ������Ʈ �����ͺ��̽����� ����ϴ� enum Ÿ�Ե� ���� */

-- ȸ��
-- member.status
-- member.role
create type member_status as enum('ENABLE', 'DISABLE');
create type member_role as enum('USER', 'ADMIN');



-- �ֹ�
-- orders.status
create type orders_status as enum('CANCEL', 'EXCHANGE', 'RETURN', 'ORDER_COMPLETE', 'SHIPMENT_COMPLETE');



-- ��ǰ
-- product.display_status
-- product.manage_status
create type product_display_status as enum('MAIN, EVENT, NONE');
create type product_manage_status as enum('STOCK', 'NON_STOCK', 'DISPLAY');



-- ��ǰ �̹���
-- product_image.status
create type product_image_status as enum('MAIN', 'SUB');