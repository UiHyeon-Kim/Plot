# Home Visual Delivery Contract

Goal: Recreate the canonical Android Home screen from `design_files/Plot Home v3.html` as a maintainable Compose UI.

User scenario: The user opens Plot and sees today's timeline, weekly review prompt, time budget, unscheduled tasks, five fixed destinations, and the profile menu.

In scope:

- Fixed light visual theme and Pretendard typography
- Android edge-to-edge system bars
- Home default and profile-menu states
- Five-item bottom navigation and floating add action
- Timeline, time blocks, current-time marker, empty placement slot, and unscheduled task drawer
- Semantic UI assertions and emulator screenshots

Out of scope:

- Persistence, accounts, network, billing, notifications, or real calendar data
- Navigation destinations beyond their visible Home entry points
- Drag-to-schedule behavior
- Web layout and iOS-only frame chrome

Acceptance criteria:

- Home uses the README colors, type scale, and five fixed destinations.
- Home v3 sample content and 54dp/hour timeline geometry are visible.
- Rounded click targets clip their ripple to the rendered shape.
- The profile menu opens above and dims the entire app chrome.
- The screen builds, passes focused tests, and is captured on the API 36.1 phone AVD.

Verification:

```text
./gradlew :app:testDebugUnitTest --rerun-tasks
./gradlew :app:connectedDebugAndroidTest --rerun-tasks
./gradlew :app:lintDebug :app:assembleDebug --rerun-tasks
```

Manual QA:

1. Launch on `Default_Phone_API_36.1` at 1080×2400, density 420.
2. Compare the default state with Home v3 layout values.
3. Open the avatar and compare menu size, order, scrim, and active ring.
4. Scroll through the unscheduled drawer and verify the fixed bottom navigation and FAB.
