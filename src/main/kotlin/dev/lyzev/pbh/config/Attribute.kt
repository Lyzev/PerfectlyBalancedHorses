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

/**
 * Represents an attribute that can be modified when breeding horses.
 * @param modify Whether the attribute should be modified.
 * @param vanillaRange The vanilla range of the attribute.
 * @param multiplier The multiplier for the attribute.
 * @param offspringRange The range of the attribute for offspring.
 * @param ignoreExcess Whether to ignore excess attribute values.
 * @param ignoreDeficit Whether to ignore deficit attribute values.
 */
data class Attribute(var modify: Boolean, val vanillaRange: ClosedRange<Double>, var multiplier: Double, var offspringRange: ClosedRange<Double>, var ignoreExcess: Boolean, var ignoreDeficit: Boolean) {
    override fun toString(): String {
        return "Attribute(modify=$modify, vanillaRange=$vanillaRange, multiplier=$multiplier, offspringRange=$offspringRange, ignoreExcess=$ignoreExcess, ignoreDeficit=$ignoreDeficit)"
    }
}
