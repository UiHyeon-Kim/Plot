# PROJECT.md — Plot (공유 진실원천)

> 프로젝트 사실/컨벤션의 **단일 사실원천**이다. 같은 내용을 다른 문서에 복제하지 않는다.
> **잘 안 변하는 사실만** 담는다. 변동 큰 기능 동작은 코드를 진실원천으로 본다.

## 정체성
- 앱: **Plot** — 일정 관리 + 투두 앱
- 패키지: `com.hanhyo.plot`
- 구조: 멀티모듈(`:core:domain/data/ui/common` + `:build-logic` + `:feature:*`). Convention Plugin 기반 — 상세는 `docs/decisions/0001-convention-plugin-multimodule.md` 참고.

## 빌드/버전
- minSdk / targetSdk / compileSdk — `gradle/libs.versions.toml` 참고 (버전은 그때그때 바뀌므로 여기 복제하지 않음)
- 명령:
  - 빌드 `./gradlew assembleDebug`
  - 테스트 `./gradlew test`
  - 릴리스 `./gradlew assembleRelease`
  - 린트 `./gradlew lint`
  - 정적 분석 `./gradlew detekt` (설정: `config/detekt/detekt.yml`)
- 의존성을 만질 때마다 AGP·Kotlin·Hilt·KSP·Compose Compiler 호환 조합을 재검증한다. "최신"과
  "호환"은 다르다 — 특히 Kotlin과 KSP는 반드시 짝을 맞춰야 한다.
- 테스트 컨벤션: `docs/TESTING.md` 참고 — **구현 변경엔 대응 테스트가 따라온다**를 기본 규칙으로 한다.

## `:app`에 남은 것
`MainActivity`(엔트리 포인트), `PlotApplication`(DI 초기화), NavHost 조립. 화면·기능 코드는 전부
`core:*`/`feature:*`로 이관한다.

## 모듈 맵
| 모듈 | 역할 |
|---|---|
| `:core:domain` | 도메인 모델·UseCase·Repository interface (순수 Kotlin) |
| `:core:data` | Repository 구현, DTO, Mapper, 원격/로컬 데이터소스 |
| `:core:ui` | 공용 테마·디자인 시스템 컴포넌트 |
| `:core:common` | 확장함수/유틸 (순수 Kotlin) |
| `:feature:<name>` | MVI ViewModel + Screen + Contract |
| `:build-logic` | Convention Plugin 정의 |

## 컨벤션 (필수)
- **테마 토큰만 사용**: 색/타이포는 `core:ui` 테마 오브젝트만. 하드코딩 금지.
- **주석**: 자명한 코드엔 주석 X. 외부 연동(서버 API, 플랫폼 SDK 등)의 동작·함정·폴백만 짧은 KDoc으로.
  *왜*만 적고 *무엇*은 코드로.
- **커밋**: conventional commit (`feat(home):`, `fix(domain):` …). PR/이슈 참조는 소스에 넣지 않음.
- **브랜치**: 장기 브랜치는 `main`(배포)·`develop`(통합). 작업 브랜치는 `<type>/<kebab-요약>`으로
  커밋 타입과 같은 어휘를 쓰고, **요약은 3단어 안쪽**으로 핵심만 남긴다
  (`feat/todo-quick-add`, `fix/alarm-timezone`, `docs/testing-guide`).
  `main`에 직접 커밋하지 않는다.
- **시크릿**: `local.properties`에만 두고 **절대 커밋 금지**.
- **패키지**: 같은 역할 파일이 2개 이상이면 역할 패키지를 만들고, 하나면 feature 루트에 둔다.
- **의존성**: 같은 configuration에서 항상 함께 쓰는 2개 이상의 의존성은 version catalog bundle을 사용한다.
- **기술 결정 기록**: 아키텍처·라이브러리 선택처럼 나중에 "왜 이렇게 했나"를 설명해야 할 결정은
  `docs/decisions/`에 ADR로 남긴다. 형식은 `docs/decisions/README.md` 참고.

## 릴리스
- 태그 `vX.Y.Z` push → `.github/workflows/release.yml`이 검증·빌드·게시.
- 게이트: `app/build.gradle.kts`의 `versionName`이 태그와 일치해야 하고,
  `docs/release-notes/<version>.md`(TEMPLATE.md 복사본, TODO 없음)가 있어야 한다.
  릴리스 본문은 이 손으로 쓴 노트이고, 라벨별 커밋 목록(`.github/release.yml`)은 그 아래에 붙는다.
- 서명은 `KEYSTORE_BASE64`/`KEYSTORE_PASSWORD`/`KEY_ALIAS`/`KEY_PASSWORD` secret이 있을 때만 수행된다.

## 후속/기술부채
- 지금 고치지 않고 넘어가는 것은 GitHub Issue에 `tech-debt` 라벨로 남긴다.
  "나중에"라고만 적힌 코드 주석은 남기지 않는다 — 이슈에 왜 미뤘는지까지 쓴다.

