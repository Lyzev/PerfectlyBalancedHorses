<div align="center">
    <img src="https://wsrv.nl/?url=https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/.idea/icon.png?raw=true&w=330&h=330" alt="Icon of Kratos">
    <br>
    <a href="https://github.com/Lyzev/PerfectlyBalancedHorses">
        <img src="https://wsrv.nl/?url=https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy-minimal/available/github_vector.svg&w=64&h=64" alt="GitHub Repository">
    </a>
    <a href="https://modrinth.com/plugin/perfectlybalancedhorses">
        <img src="https://wsrv.nl/?url=https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy-minimal/available/modrinth_vector.svg&w=64&h=64" alt="Modrinth">
    </a>
    <a href="https://lyzev.dev/discord">
        <img src="https://wsrv.nl/?url=https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy-minimal/social/discord-plural_vector.svg&w=64&h=64" alt="Discord">
    </a>
    <a href="https://github.com/Lyzev/PerfectlyBalancedHorses/pulls">
        <img src="https://wsrv.nl/?url=https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy-minimal/documentation/pull-requests_vector.svg&w=64&h=64" alt="Pull Requests">
    </a>
    <a href="https://github.com/Lyzev/PerfectlyBalancedHorses/issues">
        <img src="https://wsrv.nl/?url=https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy-minimal/documentation/issues_vector.svg&w=64&h=64" alt="Issues">
    </a>
</div>

# Perfectly Balanced Horses: Make horses great again!

<img src="https://wsrv.nl/?url=https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/assets/meme.png?raw=true&w=400" alt="Meme"/>

## Why?

Horses in Minecraft are often overlooked due to their lack of utility compared to other types of transportation, such as
elytra or boats. This plugin aims to make horses more useful by enhancing the breeding process, allowing players to
breed horses with improved stats. By modifying attributes such as movement speed, jump strength, and health, players can
create exceptionally fast, high-jumping, and healthy horses that are more competitive with other forms of
transportation.

And it is just fun to breed horses and see what you get!

## Installation

[![Modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/plugin/perfectlybalancedhorses)

1. Download the version of the plugin you need from [Modrinth](https://modrinth.com/plugin/perfectlybalancedhorses).
2. Place the downloaded JAR file in the `plugins` directory of your server.
3. Start or reload your server. (It is recommended to restart the server to ensure the plugin is loaded correctly.)
4. Configure the plugin to your liking by editing the `config.yml` file in the `plugins/PerfectlyBalancedHorses` directory.
5. Reload the plugin using the `/pbhreload` command or by restarting the server.
6. Enjoy breeding perfectly balanced horses!

> [!TIP]
> It is recommended to use the **Horse Power** client-side mod to view the horse's stats. This mod displays the
horse's speed, jump height, and health in the horse's inventory screen. It is available at the following link:
[Horse Power](https://modrinth.com/mod/horse-power).

## Description

PerfectlyBalancedHorses is a Minecraft plugin that enhances horse breeding by allowing players to breed horses with
improved stats. It modifies attributes such as movement speed, jump strength, and health, enabling the creation of
exceptionally fast, high-jumping, and healthy horses.

The plugin is also designed to be highly configurable, allowing server owners to customize the breeding process to suit
their server's needs. This includes the ability to adjust each attribute's maximum value, as well as the range of values
that can be inherited by offspring. The configuration options cover movement speed, jump strength, and health, with
additional settings to control whether excess or deficit attribute values should be ignored.

## Screenshots

![Breeding 1](https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/assets/breeding_1.png?raw=true)
![Breeding 2](https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/assets/breeding_2.png?raw=true)

## Configuration

The plugin's configuration file, `config.yml`, is located in the `plugins/PerfectlyBalancedHorses` directory. It is
divided into three sections, each corresponding to a different attribute: *speed*, *jump*, and *health*. Each section
contains the following settings:

- **modify**: A boolean value that determines whether the attribute should be modified during breeding.
- **multiplier**: A double value that determines how much the attribute should be multiplied by when breeding.
- **offspringRange**: A range of double values that determine the value that is applied to the parent's attribute value
  when breeding. The range is defined by a `start` and `end` value. If the `start` value is greater than 0, the
  offspring will inherit a value that is always greater than the parent's attribute average. Note that the `end` value
  must be greater than the `start` value. The math is as follows:
    - `parentAverage + (parentDifference + rangeBuffer) * randomFactor`
    - `randomFactor` is a random value between the `start` and `end` values.
    - `rangeBuffer` is 0.3 * the difference between the maximum (vanilla or custom) and vanilla minimum attribute value.
    - `parentDifference` is the absolute difference between the parent's attribute values.
- **ignoreExcess**: A boolean value that determines whether excess attribute values should be ignored.
- **ignoreDeficit**: A boolean value that determines whether deficit attribute values should be ignored.

The **use-vanilla-offspring-attribute-range** setting controls whether to use Minecraft's default offspring attribute
range. Set it to `true` for balanced attributes. If set to `false`, the `offspringRange` in the config file will be
used, which can result in very high or low attributes that can result in an unbalanced breeding system without steady
progression. It is recommended to set this to `true` for balanced gameplay.

Here is an example configuration file:

```yaml
use-vanilla-offspring-attribute-range: true
attributes:
  movement-speed:
    modify: true
    multiplier: 150.0
    offspring-attribute-range:
      start: -0.5
      end: 0.5
    ignore-excess: false
    ignore-deficit: false
  jump-strength:
    modify: true
    multiplier: 150.0
    offspring-attribute-range:
      start: -0.5
      end: 0.5
    ignore-excess: false
    ignore-deficit: false
  health:
    modify: true
    multiplier: 150.0
    offspring-attribute-range:
      start: -0.5
      end: 0.5
    ignore-excess: false
    ignore-deficit: false
```

> [!IMPORTANT]
> The `multiplier` setting is a percentage value. For example, a `multiplier` of 150.0 will increase the attributes
max value by 50%.

## Commands

The plugin provides the following commands:

- `/pbhreload`: Reloads the plugin's configuration file. Requires the `pbh.reload` permission.


## Bugs and Suggestions

### Discord Community

Need help with minor concerns or have questions? Join our supportive community on the [Discord server](https://lyzev.dev/discord). Our friendly members and staff are here to assist you!

[![Discord Server](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3/assets/cozy/social/discord-plural_vector.svg)](https://lyzev.dev/discord)

### GitHub Issues

For bugs or suggestions, please submit them via the [GitHub Issue Tracker](https://github.com/Lyzev/PerfectlyBalancedHorses/issues). Kindly use the provided templates and include all relevant details to ensure we can resolve your issue quickly. Your cooperation is greatly appreciated!


## Contribution Guidelines

We welcome contributions from the community! Please read our [Contribution Guidelines](https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/CONTRIBUTING.md) to get started.

## Security

If you discover a security vulnerability within this project, please refer to the [SECURITY.md](https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/SECURITY.md) file for
more information on how to report it.

**Please do not disclose security-related issues publicly.**

> [!WARNING]
> This project is under AGPL-3.0 license, which means there is no warranty for this software. Use at your own risk.
See the [LICENSE](https://github.com/Lyzev/PerfectlyBalancedHorses/blob/master/LICENSE) for more details.

## LICENSE

Copyright (C) 2025 Lyzev

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

---
*Perfectly Balanced Horses is developed and maintained by Lyzev. Thank you for using Perfectly Balanced Horses!*
