# TESTING.md

## 기본 규칙
**구현 변경엔 대응 테스트가 따라온다.** UseCase, Repository, ViewModel(상태 전이 로직)을 추가하거나
바꾸면, 같은 작업 안에서 테스트도 같이 추가/수정한다. "나중에 몰아서 쓴다"는 통하지 않는다 — 그
시점엔 이미 뭘 검증해야 하는지 잊는다.

## 계층별 테스트 위치
| 계층 | 위치 | 도구 |
|---|---|---|
| `:core:domain` UseCase | `core/domain/src/test/kotlin/...` | JUnit4, 순수 Kotlin |
| `:feature:*` ViewModel | `feature/<name>/src/test/kotlin/...` | JUnit4 + `kotlinx-coroutines-test` |
| Compose UI (선택) | `feature/<name>/src/androidTest/...` 또는 Roborazzi | 필요해지면 추가, 처음부터 넣지 않음 |

## ViewModel 테스트 패턴
`MutableStateFlow` 기반 상태는 `runTest { }` 안에서 `intent` 호출 후 `.value`를 직접 검증한다.
Turbine 같은 별도 라이브러리는 필요해질 때(다단계 emission 검증이 실제로 필요할 때) 추가한다 —
처음부터 넣지 않는다.

```kotlin
@Test
fun `Refresh intent 처리 후 state에 메시지가 반영된다`() = runTest {
    val viewModel = HomeViewModel(GetGreetingUseCase())

    viewModel.handleIntent(HomeIntent.Refresh)

    assertEquals("Hello, Plot!", viewModel.state.value.message)
}
```

UseCase가 외부 의존(Repository)을 갖게 되면 그때 Fake Repository를 만들어 주입한다. 의존이
없는 UseCase까지 Fake로 감싸지 않는다.

## 명령
```bash
./gradlew test              # 전체 유닛 테스트
./gradlew :core:domain:test # 모듈 단위
```
CI(`android-ci.yml`)가 PR마다 `./gradlew test`를 돌린다 — 테스트가 없으면 이 게이트는 통과만
하고 아무것도 검증하지 않는다는 뜻이니, 없는 채로 두지 않는다.
