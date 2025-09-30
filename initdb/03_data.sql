SET search_path TO partner;

INSERT INTO partner_org (name, code, contact_email, contact_phone, created_by, updated_by)
VALUES
    ('배민치킨',      'ORG_BAEMIN',  'contact@baeminchicken.co.kr', '02-1234-1111', 'admin', 'admin'),
    ('요기버거',      'ORG_YOGI',    'info@yogiburger.com',         '02-2222-2222', 'admin', 'admin'),
    ('쿠팡피자',      'ORG_COUPIZ',  'support@coupizza.kr',         '02-3333-3333', 'admin', 'admin');

INSERT INTO partner_store (partner_org_id, name, code, address, phone, created_by, updated_by)
VALUES
    (1, '배민치킨 강남점', 'STORE_BAEMIN_GANGNAM', '서울 강남구 테헤란로 100', '02-1234-5678', 'admin', 'admin'),
    (1, '배민치킨 홍대점', 'STORE_BAEMIN_HONGDAE', '서울 마포구 와우산로 45', '02-8765-4321', 'admin', 'admin'),
    (2, '요기버거 잠실점', 'STORE_YOGI_JAMSIL',  '서울 송파구 올림픽로 300', '02-1357-2468', 'admin', 'admin'),
    (NULL, '홍길동 돈까스', 'STORE_HGD_PORK',     '서울 동작구 상도로 50',    '02-1111-2222', 'admin', 'admin'), -- 독립 매장
    (NULL, '김가네 떡볶이','STORE_KIM_TTEOK',    '서울 중구 충무로 123',    '02-5555-6666', 'admin', 'admin');

INSERT INTO partnership (partner_org_id, partner_store_id, start_date, end_date, commission_rate, created_by, updated_by)
VALUES
    (1, NULL, '2025-01-01', NULL, 5.0, 'admin', 'admin'),  -- 배민치킨 본사 계약
    (2, NULL, '2025-02-01', NULL, 4.0, 'admin', 'admin');  -- 요기버거 본사 계약

INSERT INTO partnership (partner_org_id, partner_store_id, start_date, end_date, commission_rate, created_by, updated_by)
VALUES
    (NULL, 4, '2025-03-01', NULL, 3.5, 'admin', 'admin'),  -- 홍길동 돈까스 단독 계약
    (NULL, 5, '2025-03-15', NULL, 3.0, 'admin', 'admin');  -- 김가네 떡볶이 단독 계약