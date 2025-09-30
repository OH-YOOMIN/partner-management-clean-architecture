SET search_path TO partner;

-- 1. 제휴 주체(브랜드/법인/개별사업자 등)
CREATE TABLE partner_org (
                             partner_org_id    BIGSERIAL PRIMARY KEY,
                             name              VARCHAR(255) NOT NULL,
                             code              VARCHAR(50)  NOT NULL UNIQUE,   -- 브랜드 코드 또는 파트너 코드
                             contact_email     VARCHAR(255),
                             contact_phone     VARCHAR(50),
                             created_by        VARCHAR(100) NOT NULL,
                             created_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_by        VARCHAR(100) NOT NULL,
                             updated_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 2. 매장(브랜드 소속 또는 단독 제휴 매장)
CREATE TABLE partner_store (
                               partner_store_id  BIGSERIAL PRIMARY KEY,
                               partner_org_id    BIGINT NULL,  -- NULL이면 독립 매장
                               name              VARCHAR(255) NOT NULL,
                               code              VARCHAR(50)  NOT NULL UNIQUE,
                               address           VARCHAR(500),
                               phone             VARCHAR(50),
                               created_by        VARCHAR(100) NOT NULL,
                               created_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_by        VARCHAR(100) NOT NULL,
                               updated_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               CONSTRAINT fk_store_org
                                   FOREIGN KEY (partner_org_id)
                                       REFERENCES partner_org(partner_org_id)
);

-- 3. 제휴 계약
CREATE TABLE partnership (
                             partnership_id    BIGSERIAL PRIMARY KEY,
                             partner_org_id    BIGINT NULL,  -- 브랜드 단위 제휴
                             partner_store_id  BIGINT NULL,  -- 매장 단위 제휴
                             start_date        DATE NOT NULL,
                             end_date          DATE NULL,
                             commission_rate   NUMERIC(5,2) NOT NULL,
                             status            VARCHAR(20)  DEFAULT 'ACTIVE',   -- ACTIVE / EXPIRED / INACTIVE
                             created_by        VARCHAR(100) NOT NULL,
                             created_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_by        VARCHAR(100) NOT NULL,
                             updated_at        TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             CONSTRAINT fk_partnership_org
                                 FOREIGN KEY (partner_org_id)
                                     REFERENCES partner_org(partner_org_id),
                             CONSTRAINT fk_partnership_store
                                 FOREIGN KEY (partner_store_id)
                                     REFERENCES partner_store(partner_store_id),
                                -- 브랜드 계약이거나 매장 계약 중 하나만 허용
                             CONSTRAINT chk_partnership_level CHECK (
                                 (partner_org_id IS NOT NULL AND partner_store_id IS NULL)
                                     OR (partner_store_id IS NOT NULL)
                                 ),
                             CONSTRAINT uq_partnership_org UNIQUE (partner_org_id),
                             CONSTRAINT uq_partnership_store UNIQUE (partner_store_id)
);
