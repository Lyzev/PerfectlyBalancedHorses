/*
 * Copyright (c) 2024. Lyzev
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package dev.lyzev.pbh.config

import org.bukkit.plugin.java.JavaPlugin

/**
 * Manages the configuration values of the plugin.
 * @param plugin The plugin instance.
 */
class ConfigManager(private val plugin: JavaPlugin) {

    /**
     * The logger.
     */
    private val logger = plugin.logger

    /**
     * Whether to use the vanilla offspring attribute range.
     * @see dev.lyzev.pbh.util.AttributeCalculator.calculateAttributeBaseValue
     */
    var useVanillaOffspringAttributeRange = true

    /**
     * The movement speed attribute configuration.
     */
    val movementSpeed = Attribute(true, 0.1125..0.3375,150.0, -0.5..0.5, false, false)

    /**
     * The jump strength attribute configuration.
     */
    val jumpStrength = Attribute(true, 0.4..1.0, 150.0, -0.5..0.5, false, false)

    /**
     * The health attribute configuration.
     */
    val health = Attribute(true, 15.0..30.0, 150.0, -0.5..0.5, false, false)

    /**
     * Loads the configuration values from the plugin's config file.
     */
    fun loadConfigValues() {
        val config = plugin.config

        plugin.reloadConfig() // Reload the config file in case it was modified externally

        useVanillaOffspringAttributeRange = config.getBoolean("use-vanilla-offspring-attribute-range", true)

        movementSpeed.modify = config.getBoolean("attributes.movement-speed.modify", false)
        movementSpeed.multiplier = config.getDouble("attributes.movement-speed.multiplier", 100.0)
        if (movementSpeed.multiplier <= 0.0) {
            logger.warning("Invalid movement speed multiplier, using default value. ($movementSpeed.multiplier <= 0.0)")
            movementSpeed.multiplier = 100.0
        }
        movementSpeed.offspringRange = config.getDouble("attributes.movement-speed.offspring-attribute-range.start", -0.5)..config.getDouble("attributes.movement-speed.offspring-attribute-range.end", 0.5)
        if (movementSpeed.offspringRange.start > movementSpeed.offspringRange.endInclusive) {
            logger.warning("Invalid movement speed offspring range, using default values. (${movementSpeed.offspringRange.start} > ${movementSpeed.offspringRange.endInclusive})")
            movementSpeed.offspringRange = -0.5..0.5
        }
        movementSpeed.ignoreExcess = config.getBoolean("attributes.movement-speed.ignore-excess", false)
        movementSpeed.ignoreDeficit = config.getBoolean("attributes.movement-speed.ignore-deficit", false)

        jumpStrength.modify = config.getBoolean("attributes.jump-strength.modify", false)
        jumpStrength.multiplier = config.getDouble("attributes.jump-strength.multiplier", 100.0)
        if (jumpStrength.multiplier <= 0.0) {
            logger.warning("Invalid jump strength multiplier, using default value. ($jumpStrength.multiplier <= 0.0)")
            jumpStrength.multiplier = 100.0
        }
        jumpStrength.offspringRange = config.getDouble("attributes.jump-strength.offspring-attribute-range.start", -0.5)..config.getDouble("attributes.jump-strength.offspring-attribute-range.end", 0.5)
        if (jumpStrength.offspringRange.start > jumpStrength.offspringRange.endInclusive) {
            logger.warning("Invalid jump strength offspring range, using default values. (${jumpStrength.offspringRange.start} > ${jumpStrength.offspringRange.endInclusive})")
            jumpStrength.offspringRange = -0.5..0.5
        }
        jumpStrength.ignoreExcess = config.getBoolean("attributes.jump-strength.ignore-excess", false)
        jumpStrength.ignoreDeficit = config.getBoolean("attributes.jump-strength.ignore-deficit", false)

        health.modify = config.getBoolean("attributes.health.modify", false)
        health.multiplier = config.getDouble("attributes.health.multiplier", 100.0)
        if (health.multiplier <= 0.0) {
            logger.warning("Invalid health multiplier, using default value. ($health.multiplier <= 0.0)")
            health.multiplier = 100.0
        }
        health.offspringRange = config.getDouble("attributes.health.offspring-attribute-range.start", -0.5)..config.getDouble("attributes.health.offspring-attribute-range.end", 0.5)
        if (health.offspringRange.start > health.offspringRange.endInclusive) {
            logger.warning("Invalid health offspring range, using default values. (${health.offspringRange.start} > ${health.offspringRange.endInclusive})")
            health.offspringRange = -0.5..0.5
        }
        health.ignoreExcess = config.getBoolean("attributes.health.ignore-excess", false)
        health.ignoreDeficit = config.getBoolean("attributes.health.ignore-deficit", false)
    }

    fun logConfigValues() {
        logger.info("--- Configuration ---")
        logger.info("Use vanilla offspring attribute range: $useVanillaOffspringAttributeRange")
        logger.info("Movement speed: $movementSpeed")
        logger.info("Jump strength: $jumpStrength")
        logger.info("Health: $health")
        logger.info("--- Configuration ---")
    }

    /**
     * Saves the default configuration file if it does not exist.
     */
    init {
        plugin.saveDefaultConfig()
    }
}
