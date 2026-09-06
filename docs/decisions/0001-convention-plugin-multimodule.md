# 0001. Convention Plugin 기반 멀티모듈 채택

- 상태: 채택
- 날짜: 2026-08-27

## Context
모듈이 7개 이상(`:core:*`, `:feature:*`)으로 늘어나는 팀/대규모 프로젝트에서, 각 모듈의
`build.gradle.kts`에 `compileSdk`, `minSdk`, Java/Kotlin 버전, Compose 활성화 같은 설정을
매번 복붙하면 버전 하나 바꿀 때마다 N개 파일을 손대야 하고, 모듈마다 설정이 미묘하게
어긋나는 문제가 생긴다.

## Decision
`:build-logic` 모듈에 Convention Plugin(`AndroidApplicationConventionPlugin`,
`AndroidLibraryConventionPlugin`, `AndroidLibraryComposeConventionPlugin`,
`JvmLibraryConventionPlugin`)을 정의하고, 각 모듈은 `id("<앱명>.android.library.compose")`처럼
plugin ID 하나만 적용한다. 공통 설정 변경은 Convention Plugin 한 곳만 고치면 전 모듈에 반영된다.

## Alternatives considered
- **모듈마다 직접 설정**: 초기 진입 비용은 낮지만(소규모 프로필이 이 방식), 모듈이 늘어날수록
  중복이 선형으로 늘어나 유지보수 비용이 커진다. 4모듈 이하 소규모에는 오히려 이쪽이 더 간단해서
  `android-template-simple`(Profile B)로 별도 유지한다.
- **buildSrc**: Convention Plugin과 동일한 효과를 내지만 Gradle 9 기준 `build-logic`
  (`includeBuild`) 방식이 증분 빌드·구성 캐시 호환성이 더 좋다.

## Trade-off
초기 셋업 복잡도가 소규모 대비 높다(Convention Plugin 자체를 이해해야 함). 모듈이 4개 이하로
끝날 게 확실한 프로젝트라면 이 구조는 과설계다.
