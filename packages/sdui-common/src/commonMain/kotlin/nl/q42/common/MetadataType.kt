package nl.q42.common

import kotlin.collections.HashMap

enum class MetadataType
    (val value: String) {
    MAXIMIZE_BRIGHTNESS("maximizeBrightness"),
    REFRESH_INTERVAL("refreshInterval");

    override fun toString(): String {
        return value
    }

    companion object {
        val DEFAULT_VALUES = HashMap<MetadataType?, Any?>()

        fun from(value: String?): MetadataType? {
            for (type in MetadataType.entries) {
                if (type.value == value) {
                    return type
                }
            }

            return null
        }

        init {
            DEFAULT_VALUES.put(MetadataType.MAXIMIZE_BRIGHTNESS, false)
            DEFAULT_VALUES.put(MetadataType.REFRESH_INTERVAL, -1) // Never refreshes
        }
    }
}
