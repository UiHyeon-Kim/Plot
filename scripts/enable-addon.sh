#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
    echo "Usage: $0 <detekt|pr-template|release-notes|issue-template|renovate>"
    exit 1
fi

ADDON="$1"
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
OPT="$ROOT/templates/optional"
cd "$ROOT"

case "$ADDON" in
    detekt)
        mkdir -p config/detekt
        cp "$OPT/detekt/detekt.yml" config/detekt/detekt.yml
        cat <<'EOF'
detekt.yml을 config/detekt/detekt.yml에 복사했다. 다음을 직접 추가:

1. gradle/libs.versions.toml [plugins]에 (버전은 호환 조합 확인 후 채울 것):
   detekt = { id = "io.gitlab.arturbosch.detekt", version = "<확인 필요>" }

2. 루트 build.gradle.kts:
   plugins { alias(libs.plugins.detekt) apply false }

3. 각 모듈(또는 build-logic Convention Plugin)에:
   plugins { alias(libs.plugins.detekt) }
   detekt { config.setFrom("$rootDir/config/detekt/detekt.yml") }

4. .github/workflows/android-ci.yml에 스텝 추가:
   - name: Detekt
     run: ./gradlew detekt
EOF
        ;;
    pr-template)
        mkdir -p .github
        cp "$OPT/github/PULL_REQUEST_TEMPLATE.md" .github/PULL_REQUEST_TEMPLATE.md
        echo "Done: .github/PULL_REQUEST_TEMPLATE.md 추가."
        ;;
    release-notes)
        mkdir -p .github
        cp "$OPT/github/release.yml" .github/release.yml
        echo "Done: .github/release.yml 추가. GitHub Release 생성 시 'Generate release notes' 버튼이 라벨 기준으로 카테고리화된다."
        ;;
    issue-template)
        mkdir -p .github/ISSUE_TEMPLATE
        cp "$OPT/github/ISSUE_TEMPLATE/bug_report.md" .github/ISSUE_TEMPLATE/bug_report.md
        echo "Done: .github/ISSUE_TEMPLATE/bug_report.md 추가."
        ;;
    renovate)
        cp "$OPT/renovate.json" renovate.json
        echo "Done: renovate.json 추가. GitHub 저장소에 Renovate App 설치 필요 (https://github.com/apps/renovate)."
        ;;
    *)
        echo "Unknown addon: $ADDON"
        exit 1
        ;;
esac
