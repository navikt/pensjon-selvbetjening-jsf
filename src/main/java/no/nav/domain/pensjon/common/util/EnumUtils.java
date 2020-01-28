package no.nav.domain.pensjon.common.util;

import static java.util.Arrays.asList;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EnumSet;
import java.util.Set;

import no.stelvio.common.codestable.support.IllegalCodeEnum;

/**
 * Utility methods for the domain model
 *
 */
public final class EnumUtils {

    /**
     * Private default constructor.
     */
    private EnumUtils() {
    }

    /**
     * An extended version of the default {@link Enum#valueOf(Class, String)} suitable for enums extending IllegalCodeEnum.
     * <p>
     * Calling <code>YtelsesKompensasjonTypeCode.valueOf(&#34;8_5_1_T&#34;)</code> will result in an IllegalArgumentException as &#34;8_5_1_T&#34; is an illegal value for an enum.
     * Thus, it is named &#34;P_8_5_1_T&#34; in YtelsesKompensasjonTypeCode. This utility method is created to avoid having to check for possible illegal code enum prefixes.
     * </p>
     * <p>
     * Example
     * </p>
     * <code>
     * YtelsesKompensasjonTypeCode enumCode =
     * EnumUtils.valueOf(YtelsesKompensasjonTypeCode.class, &#34;8_5_1_T&#34;);
     * </code>
     * <p>
     * Throws NullPointerException if either enumType or name is null. Throws IllegalArgumentException if name does not exist as enum code in enumType (same behaviour as
     * Enum.valueOf())
     * </p>
     *
     * @param <T> the enum class
     * @param enumType type (class) of the enum
     * @param name name of enum attribute
     * @return an instance of an enum class
     */
    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> T valueOf(Class<T> enumType, String name) {
        if (name == null) {
            throw new NullPointerException("name is null");
        }

        if (enumType == null) {
            throw new NullPointerException("enumType is null");
        }

        try {
            // Check normal invocation first. This works for ~95% of the cases
            Method m = enumType.getMethod("valueOf", new Class[] {String.class});

            return (T) m.invoke(null, new Object[] {name});
        } catch (InvocationTargetException e) {
            // If this is an illegal code, we receive an IllegalArgumentException wrapped in an InvocationTargetException
            if (e.getCause() instanceof IllegalArgumentException) {
                try {
                    Method m = enumType.getMethod("valueOf", new Class[] {String.class});
                    T enumCode = (T) m.invoke(null, new Object[] {"P_" + name});
                    // Verify that it is not an ordinary enum code with a coincidal prefix of P_ (very unlikely!)
                    // This should work for ~4% of the cases
                    if (enumCode instanceof IllegalCodeEnum && ((IllegalCodeEnum) enumCode).getIllegalCode().equals(name)) {
                        return enumCode;
                    } else {
                        // Use brute force if not found - should work for the remaining 1%!
                        return bruteForceValueOf(enumType, name);
                    }
                } catch (Exception ex) {
                    // Last resort: use brute force - should work for the remaining 1%!
                    return bruteForceValueOf(enumType, name);
                }
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }

        throw new IllegalArgumentException("Found no code for " + name + " in enum " + enumType);
    }

    /**
     * Use brute force to achieve the same as {@link EnumUtils#valueOf(Class, String)}. This methods iterates through all enum
     * constants in the enumType class. There may be quite a lot of enum constants in an enum class, so using the brute force
     * method should be a last resort.
     * <p>
     * The brute force attempt is used for finding enum codes such as Land3TegnCode.P_UnkUnkUnk, i.e., enums codes using illegal characters. With such enum codes, it is not enough
     * to prepend &#34;P_&#34;. to the code.
     * </p>
     *
     * @param <T> the enum class
     * @param enumType type (class) of the enum
     * @param name name of enum attribute
     * @return an instance of an enum class
     * @throws IllegalArgumentException if name does not exist as enum code in enumType (same behaviour as Enum.valueOf())
     */
    private static <T extends Enum<T>> T bruteForceValueOf(Class<T> enumType, String name) {
        T[] enumConstants = enumType.getEnumConstants();

        if (enumConstants != null) {
            for (T enumConstant : enumConstants) {
                String codeValue = null;

                if (enumConstant instanceof IllegalCodeEnum) {
                    codeValue = ((IllegalCodeEnum) enumConstant).getIllegalCode();
                } else {
                    codeValue = enumConstant.name();
                }

                if (name.equals(codeValue)) {
                    return enumConstant;
                }
            }
        }

        throw new IllegalArgumentException("Found no code for " + name + " in enum " + enumType);
    }

    /**
     * Checks if the name of an enum is one of a set of target values.
     *
     * @param <T> The enum class (e.g KravlinjeStatusCode).
     * @param enumName The name to check (e.g "VILKARSPROVD")
     * @param values The set of target values to check against.
     * @return Returns <code>true</code> if the enum name matches one of the target values; <code>false</code> if no match or if
     * e is null.
     */
    public static <T extends Enum<T>> boolean isEnumNameOfValue(String enumName, T... values) {
        if (enumName == null) {
            return false;
        }

        for (T value : values) {
            // Value may be of type IllegalCodeEnum or a standard enum. In either case name() provides the correct name of
            // the enum.
            if (value.name().equals(enumName)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Convenience method for converting varargs of {@link T} to an {@link EnumSet<T>}
     */
    public static <T extends Enum<T>> Set convertToEnumSet(T... enums) {
        if (enums != null && enums.length > 0 && enums[0] != null) {
            EnumSet enumSet = EnumSet.noneOf(enums[0].getClass());
            enumSet.addAll(asList(enums));
            return enumSet;
        }
        return EnumSet.noneOf(EmptyEnumSet.class);
    }

    private enum EmptyEnumSet {
    }
}
