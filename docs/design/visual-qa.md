# Visual QA

| Screen | Reference | Rendered | Compared by | Result | Known deviation |
|---|---|---|---|---|---|
| Home default, iteration 1 | `design_files/Plot Home v3.html` | `rendered/plot-home-iteration-1.png` | HTML measurements and side-by-side inspection | Adjusted | Hour labels started 18dp too far left; empty slot border was solid. |
| Home default, iteration 2 | `design_files/Plot Home v3.html` | `rendered/plot-home-iteration-2.png` | HTML measurements and side-by-side inspection | Adjusted | First hour label clipped vertically; profile scrim did not cover bottom app chrome. |
| Home profile menu, iteration 2 | `design_files/Plot Home v3.html` | `rendered/plot-home-profile-iteration-2.png` | HTML measurements and side-by-side inspection | Adjusted | Menu matched size/order, but the bottom navigation remained undimmed. |
| Home default, final | `design_files/Plot Home v3.html` | `rendered/plot-home-final.png` | HTML measurements and side-by-side inspection | Pass | Android system status/navigation bars follow the emulator rather than the iPhone-shaped HTML frame. |
| Home profile menu, final | `design_files/Plot Home v3.html` | `rendered/plot-home-profile-final.png` | HTML measurements and side-by-side inspection | Pass | Android system status/navigation bars follow the emulator rather than the iPhone-shaped HTML frame. |

The source bundle does not include an exported reference PNG or Figma node. Pixel-diff automation is therefore deferred; visual comparison uses the explicit CSS dimensions, colors, and typography plus emulator screenshots.
