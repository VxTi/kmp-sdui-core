package nl.q42.core.util;

public class NumberUtils
{
    /**
     * Safely parse a string to a float. If the string is not a valid float, return NaN.
     *
     * @param value The string to parse.
     * @return The float value, or NaN if the string is not a valid float.
     */
    public static float parseFloatSafe( String value ) {
        try {
            return Float.parseFloat( value );
        } catch ( NumberFormatException e ) {
            return Float.NaN;
        }
    }
}
