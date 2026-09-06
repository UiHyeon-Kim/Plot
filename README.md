# Plot

일정 + 투두 Android 앱. Clean Architecture · Convention Plugin 기반 멀티모듈.

```
:app                  → 엔트리 포인트, Hilt root, NavHost 조립
:build-logic          → Convention Plugin (hanhyo.plot.android.*, hanhyo.plot.jvm.*)
:core:domain          → UseCase, Model, Repository interface (순수 Kotlin)
:core:data            → Repository 구현, DTO, Mapper, Hilt 모듈
:core:ui              → 공용 Compose 컴포넌트, 테마 (api-export)
:core:common          → 확장함수, 유틸 (순수 Kotlin)
:feature:home         → 샘플 feature (MVI ViewModel + Screen)
```

## 명령

```bash
./gradlew assembleDebug   # 빌드
./gradlew test            # 단위 테스트
./gradlew detekt          # 정적 분석 (config/detekt/detekt.yml)
./gradlew lint            # Android Lint
```

## feature 모듈 추가

```bash
mkdir -p feature/<name>/src/main/kotlin/com/hanhyo/plot/feature/<name>
# feature/<name>/build.gradle.kts 작성 → plugins { id("hanhyo.plot.android.library.compose") }
# settings.gradle.kts에 include(":feature:<name>")
# app/build.gradle.kts 의존성 + MainActivity NavHost에 라우트 추가
```

## 문서
- `PROJECT.md` — 사실/컨벤션 단일 소스
- `docs/decisions/` — 기술 결정 기록(ADR)
- `docs/TESTING.md` — 테스트 컨벤션
- `docs/release-notes/` — 릴리스별 손으로 쓴 릴리스 노트 (`TEMPLATE.md` 복사해 작성)

## 릴리스

1. `app/build.gradle.kts`의 `versionName` 갱신
2. `docs/release-notes/<version>.md` 작성 (TEMPLATE.md 복사, TODO 전부 제거)
3. `git tag v<version> && git push origin v<version>`

태그가 push되면 `.github/workflows/release.yml`이 버전·릴리스 노트를 검증하고 빌드·게시한다.
릴리스 본문은 2번에서 쓴 노트이고, 라벨별 커밋 목록은 그 아래에 자동으로 붙는다.
서명 APK/AAB는 `KEYSTORE_BASE64` 등 secret이 등록돼 있을 때만 생성된다.

## Stack
Kotlin 2.3.21 · AGP 9.2.1 · Gradle 9.4.1 · Compose BOM 2026.05.00
Hilt 2.59.2 · Navigation 2.9.8 · Coroutines 1.11.0 · KSP 2.3.8 · detekt 1.23.8
Room & DataStore는 version catalog에 있고 `:core:data/build.gradle.kts`에 주석 처리돼 있다.
