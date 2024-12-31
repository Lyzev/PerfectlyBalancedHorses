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

package dev.lyzev.pbh.listener

import dev.lyzev.pbh.config.ConfigManager
import dev.lyzev.pbh.util.AttributeCalculator
import org.bukkit.attribute.Attribute
import org.bukkit.entity.Horse
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityBreedEvent
import java.util.logging.Logger

/**
 * Listens for horse breeding events.
 * @param configManager The configuration manager.
 * @param logger The logger.
 */
class HorseBreedingListener(private val configManager: ConfigManager, private val logger: Logger) : Listener {

    /**
     * Modifies the attributes of a foal when two horses breed.
     */
    @EventHandler
    fun onEntityBreed(event: EntityBreedEvent) {
        val foal = event.entity
        if (foal is Horse) {
            val mother = event.mother as Horse
            val father = event.father as Horse
            logger.info("Breeding horses: Mother(${mother.name}, ${mother.uniqueId}), Father(${father.name}, ${father.uniqueId})")
            val movementSpeed = configManager.movementSpeed
            if (movementSpeed.modify) {
                foal.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue =
                    AttributeCalculator.calculateAttributeBaseValue(
                        mother.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue ?: movementSpeed.vanillaRange.start,
                        father.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED)?.baseValue ?: movementSpeed.vanillaRange.start,
                        configManager.movementSpeed.vanillaRange.endInclusive * configManager.movementSpeed.multiplier / 100.0,
                        movementSpeed.vanillaRange.start,
                        movementSpeed.vanillaRange.endInclusive,
                        configManager.useVanillaOffspringAttributeRange,
                        movementSpeed.offspringRange,
                        movementSpeed.ignoreExcess,
                        movementSpeed.ignoreDeficit
                    ).apply { logger.info("Foal movement speed: $this (modified)") }
            } else {
                logger.info("Foal movement speed: ${configManager.movementSpeed} (not modified)")
            }
            val jumpStrength = configManager.jumpStrength
            if (jumpStrength.modify) {
                foal.getAttribute(Attribute.HORSE_JUMP_STRENGTH)?.baseValue =
                    AttributeCalculator.calculateAttributeBaseValue(
                        mother.getAttribute(Attribute.HORSE_JUMP_STRENGTH)?.baseValue ?: jumpStrength.vanillaRange.start,
                        father.getAttribute(Attribute.HORSE_JUMP_STRENGTH)?.baseValue ?: jumpStrength.vanillaRange.start,
                        configManager.jumpStrength.vanillaRange.endInclusive * configManager.jumpStrength.multiplier / 100.0,
                        jumpStrength.vanillaRange.start,
                        jumpStrength.vanillaRange.endInclusive,
                        configManager.useVanillaOffspringAttributeRange,
                        jumpStrength.offspringRange,
                        jumpStrength.ignoreExcess,
                        jumpStrength.ignoreDeficit
                    ).apply { logger.info("Foal jump strength: $this (modified)") }
            } else {
                logger.info("Foal jump strength: ${configManager.jumpStrength} (not modified)")
            }
            val health = configManager.health
            if (health.modify) {
                foal.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue =
                    AttributeCalculator.calculateAttributeBaseValue(
                        mother.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: health.vanillaRange.start,
                        father.getAttribute(Attribute.GENERIC_MAX_HEALTH)?.baseValue ?: health.vanillaRange.start,
                        configManager.health.vanillaRange.endInclusive * configManager.health.multiplier / 100.0,
                        health.vanillaRange.start,
                        health.vanillaRange.endInclusive,
                        configManager.useVanillaOffspringAttributeRange,
                        health.offspringRange,
                        health.ignoreExcess,
                        health.ignoreDeficit
                    ).apply { logger.info("Foal health: $this (modified)") }
            } else {
                logger.info("Foal health: ${configManager.health} (not modified)")
            }
        }
    }
}
