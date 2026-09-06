# 0001. Convention Plugin 기반 멀티모듈 채택

- 상태: 채택
- 날짜: 2026-09-06

## Context
Plot은 일정 관리와 투두를 함께 다루는 앱이라, 화면이 캘린더·투두 목록·상세·통계·설정·알림처럼
갈라질 것으로 봤다. 지금은 1인 개발이지만 feature가 5개 이상으로 늘어나는 구조다.

모듈마다 `build.gradle.kts`에 `compileSdk`, `minSdk`, Java/Kotlin 타깃, Compose 활성화, Hilt·KSP
적용을 반복해 적으면 두 가지가 문제가 된다. 버전 하나 올릴 때 N개 파일을 고쳐야 하고, 그러다
모듈마다 설정이 미묘하게 어긋난다. 어긋난 설정은 빌드가 깨질 때까지 드러나지 않는다.

## Decision
`:build-logic`에 Convention Plugin을 정의하고, 각 모듈은 plugin ID 하나만 적용한다.

| Plugin ID | 대상 |
|---|---|
| `hanhyo.plot.android.application` | `:app` |
| `hanhyo.plot.android.library` | `:core:data` |
| `hanhyo.plot.android.library.compose` | `:core:ui` |
| `hanhyo.plot.jvm.library` | `:core:domain`, `:core:common` |
| `hanhyo.plot.android.hilt` | Hilt + KSP가 필요한 모듈 |
| `hanhyo.plot.android.feature` | `:feature:*` — Hilt + `:core:ui` 의존 + Navigation·Serialization |

SDK 레벨은 `gradle/libs.versions.toml`에 두고 Convention Plugin이 읽는다. 버전 정보가 카탈로그
한 곳에만 존재하게 하기 위해서다.

의존성 출처도 하나로 고정한다. **Compose UI는 `:core:ui`가 `api`로 재노출**하고,
`hanhyo.plot.android.feature`는 `:core:ui` 의존과 화면 아키텍처 라이브러리(Navigation,
hilt-navigation-compose, lifecycle-viewmodel-compose)만 더한다. 둘 다 Compose를 추가하면
같은 의존성이 두 경로로 들어와, 나중에 버전을 조정할 때 어디를 고쳐야 하는지가 흐려진다.

## Alternatives considered
- **모듈마다 직접 설정**: 초기 진입 비용이 없다. 모듈이 4개 이하로 끝난다면 이쪽이 더 간단하다.
  Plot은 feature가 계속 늘어나는 구조라 중복이 선형으로 쌓이는 쪽을 피했다.
- **buildSrc**: 같은 효과를 내지만, `buildSrc`는 내용이 바뀌면 전체 빌드 스크립트가 무효화된다.
  `includeBuild("build-logic")` 방식이 증분 빌드와 구성 캐시에 더 유리하다.

## Trade-off
Convention Plugin 자체를 이해해야 모듈을 추가할 수 있다. 설정이 어디서 오는지가 모듈
`build.gradle.kts`만 봐서는 안 보이므로, 새 설정을 넣을 때 Convention Plugin과 모듈 중 어디에
둘지 매번 판단해야 한다.

feature가 예상과 달리 3~4개에서 멈춘다면 이 구조는 과설계다. 그때는 `:build-logic`을 걷어내고
모듈별 설정으로 되돌리는 편이 낫다.
