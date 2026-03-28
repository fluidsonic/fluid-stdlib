# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/).

## [0.15.0] - 2026-03-28

### Changed

- Migrated to fluid-gradle 3.0.0 (Kotlin 2.3, Gradle 9.4.1, JDK 21+).
- Updated fluid-locale from 0.13.0 to 0.14.0.
- Improved test coverage across all modules.

### Added

- JS target support.
- KDoc for all public API.
- GitHub Actions CI for JS tests.

### Fixed

- Resolved Dokka link warnings by qualifying receiver property references in KDoc.
- Suppressed expect/actual classes beta warnings with `-Xexpect-actual-classes` compiler flag.

### Removed

- Removed Darwin targets (iOS, macOS, tvOS, watchOS).
