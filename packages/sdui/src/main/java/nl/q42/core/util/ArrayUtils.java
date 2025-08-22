package nl.q42.core.util;

public class ArrayUtils
{
    public static <T> T[] shift(T[] array, int offset)
    {
        if (array == null || offset >= array.length)
            return null;

        //noinspection unchecked
        T[] result = (T[]) java.lang.reflect.Array.newInstance(array.getClass().getComponentType(), array.length - offset);
        System.arraycopy(array, offset, result, 0, result.length);
        return result;
    }

    public static <T> T[] shift(T[] array)
    {
        return shift(array, 1);
    }

    public static <T> T[] pop(T[] array)
    {
        if (array == null || array.length == 0)
            return null;

        //noinspection unchecked
        T[] result = (T[]) new Object[array.length - 1];
        System.arraycopy(array, 0, result, 0, result.length);
        return result;
    }
}
