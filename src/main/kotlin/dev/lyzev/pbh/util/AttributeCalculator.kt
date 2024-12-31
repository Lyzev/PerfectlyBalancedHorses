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

package dev.lyzev.pbh.util

import kotlin.math.abs
import kotlin.random.Random.Default.nextDouble

/**
 * A utility class that calculates the base value of an attribute for a foal based on the parents' attributes.
 */
object AttributeCalculator {

    /**
     * Calculates the base value of an attribute for a foal based on the parents' attributes.
     * This method is a modified version of the official Minecraft horse breeding algorithm from the 1.21-version source code.
     */
    fun calculateAttributeBaseValue(
        parentBase: Double,
        otherParentBase: Double,
        max: Double,
        vanillaMin: Double,
        vanillaMax: Double,
        useVanillaOffspringAttributeRange: Boolean,
        offspringAttributeRange: ClosedRange<Double>,
        ignoreExcess: Boolean,
        ignoreDeficit: Boolean
    ): Double {
        var parentBase = parentBase
        var otherParentBase = otherParentBase
        parentBase = parentBase.coerceIn(vanillaMin, max)
        otherParentBase = otherParentBase.coerceIn(vanillaMin, max)

        val rangeBuffer = 0.3 * ((if (useVanillaOffspringAttributeRange) vanillaMax else max) - vanillaMin)
        val totalRangeDifference = abs(parentBase - otherParentBase) + rangeBuffer
        val averageBaseValue = (parentBase + otherParentBase) / 2.0
        val randomFactor = nextDouble(offspringAttributeRange.start, offspringAttributeRange.endInclusive)
        val finalBaseValue = averageBaseValue + totalRangeDifference * randomFactor

        return if (finalBaseValue > max && !ignoreExcess) {
            val excess = finalBaseValue - max
            max - excess
        } else if (finalBaseValue < vanillaMin && !ignoreDeficit) {
            val deficit = vanillaMin - finalBaseValue
            vanillaMin + deficit
        } else {
            finalBaseValue
        }
    }

}
