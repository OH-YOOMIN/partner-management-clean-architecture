# Partner Management Sample Project (Multi-Module + Clean Architecture)

이 프로젝트는 기존 **멀티 모듈 + 레이어드 아키텍처**를 **멀티 모듈 + 클린 아키텍처(DDD 기반)** 로 재설계하면서 학습한 내용을 재구성한 **샘플 프로젝트**입니다.

학습 후 실무에서 적용했던 구조 개선 과정을 축소/각색하여, 핵심적인 설계 패턴만을 정리하였습니다.

---

## 프로젝트 목표
- 멀티모듈 + 레이어드 아키텍처 기반 코드를 **멀티 모듈 + 클린 아키텍처**로 재설계
- 도메인 로직을 `domain` 모듈로 완전히 분리하여 **캡슐화 강화**
- **포트-어댑터 패턴**을 통해 애플리케이션 핵심 로직과 외부 의존성 분리
- 운영 환경에서 요구되는 **확장성과 유지보수성 검증**

---

## ⚙️ Tech Stack
- **Language**: Java 11  
- **Framework**: Spring Boot 2.6.12
- **Architecture**: Clean Architecture (DDD, Ports & Adapters), Multi-Module  
- **ORM**: JPA, QueryDSL  
- **Database**: PostgreSQL (Docker Compose 제공)  
- **Build Tool**: Gradle  

---

## 📂 모듈 구조
```
partner
├── partner-app-api # Spring Boot Application (실행 모듈)
├── partner-application # 유즈케이스 구현, 트랜잭션 관리
├── partner-domain # 도메인 모델 및 규칙 (비즈니스 로직 핵심)
├── partner-adapter # Inbound Adapter (REST Controller, Request/Response DTO)
├── partner-infrastructure # Outbound Adapter (DB, external client 등에 대한 기술 및 구현체)
└── partner-common # 공통 모듈
```
---

## 🚀 실행 방법
1. PostgreSQL 실행 (Docker)
   ```bash
   docker-compose up -d

2. 애플리케이션 실행 : 별도의 프로파일 지정 없이 application.yml이 적용됩니다.

3. Swagger 접속 : http://localhost:9090/swagger-ui/index.html

---

## 📌 주요 기능

### 파트너 조직 (Partner Organization) 관리
- 조직 등록, 조회

📍 Request 샘플 (조직 등록)
```http
POST /v1/partner/organizations
Content-Type: application/json

{
  "name": "조직명",
  "code": "ORG_CODE",
  "contactEmail": "admin@org.com",
  "contactPhone": "010-1234-5678",
  "createdBy": "system_admin"
}
```

### 파트너 매장 (Partner Store) 관리
- 매장 조회

### 제휴 (Partnership) 관리
- 제휴 조회

➡️ 그 외 API 상세 스펙은 Swagger UI에서 확인 가능합니다.
