package nl.q42.common

import java.util.Optional
import kotlin.collections.HashMap
import kotlin.collections.MutableMap

enum class MetadataType
    (val value: String) {
    MAXIMIZE_BRIGHTNESS("maximizeBrightness"),
    REFRESH_INTERVAL("refreshInterval");

    override fun toString(): String {
        return value
    }

    companion object {
        val DEFAULT_VALUES: MutableMap<MetadataType?, Any?>

        fun from(value: String?): Optional<MetadataType?> {
            for (type in MetadataType.entries) {
                if (type.value == value) {
                    return Optional.of<MetadataType?>(type)
                }
            }

            return Optional.empty<MetadataType?>()
        }

        init {
            DEFAULT_VALUES = HashMap<MetadataType?, Any?>()
            DEFAULT_VALUES.put(MetadataType.MAXIMIZE_BRIGHTNESS, false)
            DEFAULT_VALUES.put(MetadataType.REFRESH_INTERVAL, -1) // Never refreshes
        }
    }
}
