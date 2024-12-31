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

package dev.lyzev.pbh

import dev.lyzev.pbh.command.ReloadCommand
import dev.lyzev.pbh.config.ConfigManager
import dev.lyzev.pbh.listener.HorseBreedingListener
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin
import java.lang.System

class PerfectlyBalancedHorses : JavaPlugin(), Listener {

    /**
     * The logger.
     */
    private val logger = getLogger()

    /**
     * The configuration manager.
     */
    private lateinit var configManager: ConfigManager

    /**
     * The horse breeding event listener.
     */
    private lateinit var horseBreedingListener: HorseBreedingListener

    override fun onEnable() {
        val start = System.currentTimeMillis()
        logger.info("Perfectly Balanced Horses is loading...")

        logger.info("Loading configuration...")
        configManager = ConfigManager(this)
        configManager.loadConfigValues()
        logger.info("Configuration loaded.")

        configManager.logConfigValues()

        logger.info("Registering event listeners...")
        horseBreedingListener = HorseBreedingListener(configManager, logger)
        server.pluginManager.registerEvents(horseBreedingListener, this)
        logger.info("Event listeners registered.")

        logger.info("Registering commands...")
        getCommand("pbhreload")?.setExecutor(ReloadCommand(configManager, logger))
        logger.info("Commands registered.")

        logger.info("Perfectly Balanced Horses has loaded in ${System.currentTimeMillis() - start}ms.")
    }

    override fun onDisable() {
        logger.info("Perfectly Balanced Horses has unloaded.")
    }
}
