# RandomPortion

A Paper Minecraft plugin that gives players a random status effect whenever they take damage.

## Overview

RandomPortion turns ordinary damage into a random-effect event. Whenever a player takes damage while the plugin is enabled, the plugin selects one status effect from Minecraft's registered mob effects and applies it to that player.

The effect duration is randomized between **10 and 60 seconds**.

## Features

- Applies a random Minecraft status effect when a player takes damage
- Uses Minecraft's registered mob-effect registry, so the random pool is not limited to a small hard-coded list
- Random effect duration from **10 to 60 seconds**
- Effect amplifier is **0** (level I)
- Sends the affected player a message showing the effect name and duration
- Global ON/OFF control without unloading the plugin
- Broadcasts a server message when the feature is enabled or disabled
- Admin command is restricted to OPs by default
- `/rp` alias for the main command

## Requirements

- Paper 26.2
- Java 25
- Gradle

## Commands

| Command | Description |
|---|---|
| `/randomportion on` | Enables random effects on player damage |
| `/randomportion off` | Disables random effects on player damage |
| `/rp on` | Short alias for enabling the plugin feature |
| `/rp off` | Short alias for disabling the plugin feature |

## Permission

`randomportion.admin`

The permission defaults to **OP**, so normal players cannot use the management command unless permission is granted separately.

## How It Works

1. A player takes damage.
2. If RandomPortion is enabled, the plugin chooses a random status effect.
3. A duration between 10 and 60 seconds is selected.
4. The effect is applied at level I.
5. The player receives a chat message showing the selected effect and duration.

Example message:

`랜덤 효과 획득! speed (24초)`

## Installation

1. Download the JAR from the latest GitHub Release.
2. Put the JAR into your Paper server's `plugins` folder.
3. Start or restart the server.
4. Make sure you are OP, or have the `randomportion.admin` permission.
5. Use `/randomportion on` or `/rp on` to enable the feature.

## Building From Source

On Windows:

```powershell
.\gradlew build
```

On Linux/macOS:

```bash
./gradlew build
```

The built JAR will be created inside:

`build/libs/`

## Plugin Information

- Plugin name: **RandomPortion**
- Version: **1.0.0**
- Main class: `me.woomin.randomportion.RandomPortion`
- API version: **26.2**
- Author: **woomin**

## Notes

The plugin starts with its random-effect feature enabled when the server loads. Use `/randomportion off` if you want to temporarily disable the effect without removing the plugin.
