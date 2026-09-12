# Nuvyra Launcher — Codespaces source package

This package is prepared for GitHub and GitHub Codespaces. It contains the editable source and project configuration for Nuvyra Launcher. APKs, signing keys, API keys, generated build output, and private credentials are intentionally excluded.

## Build

After opening the repository in Codespaces, run:

```bash
chmod +x gradlew
./gradlew :NuvyraLauncher:assembleRelease -Darch=all
```

The project requires a compatible Java version and Android SDK/NDK. The intended development environment is described in `.devcontainer/devcontainer.json`.

For CurseForge integration, provide an officially issued API key through a local environment variable or an ignored local file. Never commit API keys, OAuth credentials, signing keys, or passwords to GitHub.

## Security and transparency

Review source changes and third-party dependencies before building. Do not install unknown APKs, JAR files, native libraries, or scripts. The source package deliberately excludes binary runtime assets and generated files so that the public repository remains small and auditable.

This project preserves the applicable GPL license, upstream copyright notices, contributor credits, and third-party license notices. See `LICENSE` and `NOTICE_WALKsys.md` before redistributing modified builds.
