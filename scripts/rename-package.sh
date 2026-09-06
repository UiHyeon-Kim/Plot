#!/usr/bin/env bash
set -euo pipefail

if [[ $# -lt 1 ]]; then
    echo "Usage: $0 <new-package> [AppName]"
    echo "  Personal: $0 com.hanhyo.myapp MyApp"
    echo "  Team:     $0 com.team.myapp  MyApp"
    exit 1
fi

NEW_PKG="$1"
APP_NAME="${2:-$(echo "$NEW_PKG" | awk -F. '{print $NF}' | awk '{print toupper(substr($0,1,1)) substr($0,2)}')}"
OLD_PKG="com.hanhyo.template"
ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"

if [[ "$(uname)" == "Darwin" ]]; then
    sedi() { sed -i '' "$@"; }
else
    sedi() { sed -i "$@"; }
fi

echo "Package : $OLD_PKG → $NEW_PKG"
echo "App name: $APP_NAME"
cd "$ROOT"

# Replace package strings in source files
find . \( -name "*.kt" -o -name "*.gradle.kts" -o -name "*.xml" \) \
    -not -path "*/build/*" -not -path "*/.gradle/*" | while read -r file; do
    sedi "s|$OLD_PKG|$NEW_PKG|g" "$file"
done

# Replace package strings + <AppName>/<package> placeholders in project docs
find . -name "*.md" -not -path "*/build/*" -not -path "*/.gradle/*" | while read -r file; do
    sedi "s|$OLD_PKG|$NEW_PKG|g; s|<AppName>|$APP_NAME|g; s|<package>|$NEW_PKG|g" "$file"
done

# Rename Kotlin source directories (deepest first)
OLD_PATH="$(echo "$OLD_PKG" | tr '.' '/')"
NEW_PATH="$(echo "$NEW_PKG" | tr '.' '/')"
find . -type d -path "*/$OLD_PATH" -not -path "*/build/*" | sort -r | while read -r dir; do
    new_dir="${dir/$OLD_PATH/$NEW_PATH}"
    mkdir -p "$new_dir"
    mv "$dir"/* "$new_dir/" 2>/dev/null || true
    rmdir "$dir" 2>/dev/null || true
done

# Update app_name in strings.xml
find . -name "strings.xml" -not -path "*/build/*" | while read -r file; do
    sedi "s|TemplateSimple|$APP_NAME|g; s|TemplateLarge|$APP_NAME|g" "$file"
done

echo ""
echo "Done. Next steps:"
echo "  1. Open in Android Studio"
echo "  2. File > Sync Project with Gradle Files"
