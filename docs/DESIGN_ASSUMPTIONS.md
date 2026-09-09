# Design Assumptions

The product rules in `README.md` take precedence over stale annotations inside the HTML files.

| ID | Area | Evidence | Assumption | Confidence | Impact | Follow-up |
|---|---|---|---|---|---|---|
| D-001 | Bottom navigation | README and rendered Home v3 markup | Use five fixed destinations: Home, Lists, Calendar, Matrix, Habits. Ignore Home v3 prose that still says four tabs. | High | Shared app chrome | Remove stale HTML notes when the source bundle is revised. |
| D-002 | Theme | README | The application is light-only. Dark examples in the older design-system page are not implemented. | High | Whole app | None. |
| D-003 | Warning color | README | Use amber `#B45309`; do not use orange or the stale Home CSS value `#92400E`. | High | Shared semantic color | None. |
| D-004 | Type scale | README and Home HTML | Normalize fractional HTML sizes to 11/12/13/15/17/22sp. | High | Shared typography | Confirm if the HTML type scale is later promoted over README. |
| D-005 | System bars | Android platform behavior | The iPhone frame, Dynamic Island, and 52px simulated status bar are documentation chrome, not app UI. Use Android edge-to-edge insets and light system icons. | High | App shell | Compare again on another Android phone profile. |
| D-006 | Reference mode | Local HTML/CSS and JSX | Home v3 is the visual fixture; JSX is only the drag behavior fixture. | High | Home fixture | Obtain exported PNG or Figma nodes for automated pixel-diff baselines. |
| D-007 | Runtime data | Static design handoff | The launched screen uses isolated sample UI data so visual calibration can proceed before repositories exist. | High | Home behavior | Replace with real state only when functional delivery starts. |
| D-008 | Font asset | Locally installed Pretendard OTF files | Bundle Regular, Medium, SemiBold, and Bold to keep the Korean text metrics stable. | High | APK size and typography | Verify font redistribution/license packaging before release publication. |
