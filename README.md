# Partner Management Sample Project (Multi-Module + Clean Architecture)

이 프로젝트는 기존 **멀티 모듈 + 레이어드 아키텍처**를 **멀티 모듈 + 클린 아키텍처(DDD 기반)** 로 재설계하면서 학습한 내용을 재구성한 **샘플 프로젝트**입니다.

학습 후 실무에서 적용했던 구조 개선 과정을 축소/각색하여, 핵심적인 설계 패턴만을 정리하였습니다.

---

## 📝 배경 및 동기

실무에서 다음과 같은 문제를 경험했습니다:

- **도메인 비대화**: 시간이 지남에 따라 도메인이 커지고 복잡해지면서, 유지보수 난이도가 점차 증가
- **동시다발적인 프로젝트 진행**: 여러 팀에서 다양한 요구사항이 동시에 발생하면서 코드 변경 충돌과 영향 범위 예측이 어려워짐
- **레이어드 아키텍처 한계**: 기존 레이어드 아키텍처에서는 각 계층 간 역할이 자주 침범되어, 도메인 규칙이 어댑터/서비스 계층에 흩어져 버리는 문제가 발생

이를 개선하기 위해 **클린 아키텍처(DDD, Ports & Adapters)** 개념을 학습하고,  
멀티 모듈 기반 구조로 재설계하여 **도메인 중심 설계, 의존성 역전, 캡슐화 강화**를 실험했습니다.  

본 프로젝트는 그 학습 경험을 바탕으로 재구성한 **샘플 프로젝트**입니다.

---

## 🎯 프로젝트 목표
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
├── partner-app # Spring Boot Application (실행 모듈)
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
