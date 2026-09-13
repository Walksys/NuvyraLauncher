<div align="center"> <img src="https://raw.githubusercontent.com/Walksys/NuvyraLauncher/main/NuvyraLauncher/src/main/res/drawable-nodpi/nuvyra_launcher_logo.png" width="180" alt="Nuvyra Launcher" />

# Nuvyra Launcher

**A Minecraft: Java Edition launcher for Android.**

[Telegram](https://t.me/NuvyraLauncher) · [Discord](https://discord.gg/KSRp5tA27f) · [Releases](https://github.com/Walksys/NuvyraLauncher/releases)

</div>

## Introduction

Nuvyra Launcher lets you play Minecraft: Java Edition on Android through a modern, configurable launcher interface. It provides tools for installing and managing game versions, Java runtimes, profiles, mods, modpacks, resource packs, shader packs, saves, controls, renderers, and graphics settings.

Nuvyra Launcher is maintained by **Walksys** as an independent community project.

## Performance-focused

Nuvyra Launcher is built with performance and compatibility in mind. The project includes improvements to launcher startup, game profile management, renderer configuration, Java runtime handling, memory settings, and mobile controls. These improvements can help reduce unnecessary overhead, stuttering, and configuration problems compared with a default setup, while giving users more control over the settings that affect FPS and frame pacing.

Actual performance depends on the device, GPU driver, renderer, Minecraft version, shader pack, resource pack, and installed mods. Nuvyra Launcher does not promise one fixed FPS result for every device; instead, it provides practical tools to help users get the best performance from their own hardware.

## Everything in one launcher

Nuvyra Launcher gives players more than a basic start button. Its advanced control system is designed for touchscreens, physical keyboards, mice, and gamepads, with customizable layouts and responsive in-game controls. Players can also manage their Minecraft content from inside the launcher, including mod downloads, modpacks, worlds, saves, resource packs, and shader packs where the relevant platform and author permissions allow it.

The in-game overlay can display useful information such as FPS and memory usage, making it easier to monitor performance while playing and adjust resolution, renderer, memory, and control settings without leaving the launcher workflow.

## Getting Nuvyra Launcher

You can get Nuvyra Launcher in the following ways:

- Download a stable APK from the [Releases](https://github.com/Walksys/NuvyraLauncher/releases) page.

- Download development builds from GitHub Actions when available.

- Build the launcher yourself from this repository.

- Join the [Telegram community](https://t.me/NuvyraLauncher) or [Discord server](https://discord.gg/KSRp5tA27f) for announcements and support.

When a universal APK is provided, it supports the Android ABIs included in that release. Always download builds from a trusted release source and verify the release information before installing.

## Building

### Requirements

- Android Studio or GitHub Codespaces.

- A compatible Java Development Kit.

- Android SDK and the Android SDK Build Tools.

- Android NDK version required by the Gradle configuration.

- Enough disk space for Android dependencies and optional runtime assets.

### Build the launcher

From the repository root, run:

```bash
chmod +x gradlew
./gradlew :NuvyraLauncher:assembleRelease -Darch=all
```

For a debug build:

```bash
./gradlew :NuvyraLauncher:assembleDebug
```

On Windows, use `gradlew.bat` instead of `./gradlew`.

The repository includes `.devcontainer/devcontainer.json` for GitHub Codespaces. Do not commit signing keys, passwords, OAuth credentials, or API keys. CurseForge access must use an officially issued API key provided through local, ignored configuration.

## Features

- Minecraft: Java Edition version installation and management.

- Java runtime and isolated profile management.

- Forge, Fabric, and other supported mod-loader workflows.

- Mod, modpack, resource-pack, and shader-pack management.

- Microsoft account authentication through supported official flows.

- Save management and file management.

- Custom controls, renderer selection, resolution, memory, and graphics settings.

- Advanced touchscreen, keyboard, mouse, and gamepad controls.

- In-launcher management of mods, modpacks, worlds, saves, resource packs, and shaders.

- Optional in-game FPS and memory display for quick performance monitoring.

- Material 3 user interface with **Glacier** as the default color theme.

- Multi-architecture Android builds.

- Performance-oriented settings for resolution, memory, renderer, and frame pacing.

## Current roadmap

- Improve first-run setup and profile management.

- Improve modpack import and dependency handling.

- Improve renderer compatibility across Android GPUs.

- Improve download reliability and progress reporting.

- Expand diagnostics for launcher and game startup errors.

- Improve documentation and community support.

## Known limitations

- Performance and compatibility vary between devices, Android versions, GPU drivers, renderers, Minecraft versions, and mod combinations.

- Some shaders, large texture atlases, and graphics-heavy modpacks may require a different renderer or device-specific settings.

- Third-party content may have separate licenses, download restrictions, or author requirements.

## Contributing

Contributions are welcome. You can help with code, documentation, translations, testing, issue reports, and compatibility feedback.

Before opening a pull request, explain what changed and include clear steps to test it. Please remove passwords, access tokens, API keys, account identifiers, and private file paths from logs and screenshots.

## ⚠️ Important Setup (Offline / Non-Premium Accounts Trick)

- If you want to play Minecraft using an offline / non-premium (cracked) account, follow this quick trick to bypass the limit:

- 1. Open your file manager and go to this exact path:
com.nuvyra.launcher/files/
- 2. Inside the files folder, create a new empty file and name it exactly:
```
circumventLimit
```

Once this file is created in the path above, open the launcher and you can log in with your offline account smoothly! Enjoy! 🔥✨

## License

Nuvyra Launcher is free and open-source software. See [`LICENSE`](LICENSE) and [`NOTICE_Walksys.md`](NOTICE_Walksys.md) for the applicable license and required project notices.

Nuvyra Launcher is not an official product of Mojang, Microsoft, CurseForge, Overwolf, or any other platform.

## Community

- Telegram: [https://t.me/NuvyraLauncher](https://t.me/NuvyraLauncher)

- Discord: [https://discord.gg/KSRp5tA27f](https://discord.gg/KSRp5tA27f)

- GitHub: [https://github.com/Walksys/NuvyraLauncher](https://github.com/Walksys/NuvyraLauncher)

Please use the community channels for announcements, troubleshooting, compatibility reports, and development discussions. Do not share private credentials publicly.
