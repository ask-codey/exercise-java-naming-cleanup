import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The tests reach the bakery's code by its new names through these helpers, so the tests compile before you have
 * renamed anything. Each helper fails with a message that says which name is missing. You don't need to change this
 * file.
 */
final class Check {

    /** The package every class belongs in, once it's renamed. */
    static final String PACKAGE = "com.maplestreetbakery.orders";

    private Check() {
    }

    /** The class or interface with this name, in the bakery's package, or a failure that says what to rename. */
    static Class<?> type(String name) {
        try {
            return Class.forName(PACKAGE + "." + name);
        } catch (ClassNotFoundException e) {
            fail("There's no " + name + " in the package " + PACKAGE + " yet. Rename the package (step 1 of the README), "
                    + "then the class or interface, to " + name + ".");
            return null;
        }
    }

    /** A private variable with this name and type. */
    static void privateVariable(String className, String name, Class<?> type) {
        Field field = field(className, name);
        assertEquals(type, field.getType(), className + "'s variable " + name + " should be a " + type.getSimpleName() + ".");
        assertTrue(Modifier.isPrivate(field.getModifiers()), className + "'s variable " + name + " should be private.");
    }

    /** A public static final constant with this name and value. */
    static void constant(String className, String name, Object value) {
        Field field = field(className, name);
        int modifiers = field.getModifiers();
        assertTrue(Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers),
                className + "." + name + " should be a public static final constant.");
        try {
            assertEquals(value, field.get(null), className + "." + name + " should be " + value + ".");
        } catch (IllegalAccessException e) {
            fail(className + "." + name + " can't be read: make it public.");
        }
    }

    /** A public method with this name and these parameter types, without calling it. */
    static void hasMethod(String className, String name, Class<?>... parameters) {
        method(type(className), name, parameters);
    }

    /** A new object, made with the public constructor that takes these arguments. */
    static Object create(String className, Object... arguments) {
        Class<?>[] types = typesOf(arguments);
        try {
            Constructor<?> constructor = type(className).getConstructor(types);
            return constructor.newInstance(arguments);
        } catch (NoSuchMethodException e) {
            fail(className + " needs a public constructor that takes " + names(types) + ".");
        } catch (InvocationTargetException e) {
            fail("new " + className + "(" + values(arguments) + ") stopped with " + e.getCause());
        } catch (ReflectiveOperationException e) {
            fail("new " + className + "(" + values(arguments) + ") didn't work: " + e);
        }
        return null;
    }

    /** Calls the public method with this name on target, and returns what it returns. */
    static Object call(Object target, String name, Object... arguments) {
        Method method = method(target.getClass(), name, typesOf(arguments));
        try {
            return method.invoke(target, arguments);
        } catch (InvocationTargetException e) {
            fail(target.getClass().getSimpleName() + "." + name + "(" + values(arguments) + ") stopped with " + e.getCause());
        } catch (IllegalAccessException e) {
            fail(target.getClass().getSimpleName() + "." + name + " should be public.");
        }
        return null;
    }

    private static Method method(Class<?> type, String name, Class<?>[] parameters) {
        try {
            return type.getMethod(name, parameters);
        } catch (NoSuchMethodException e) {
            fail(type.getSimpleName() + " needs a public method named " + name + "(" + names(parameters) + "). "
                    + "Rename the method that does this job.");
            return null;
        }
    }

    private static Field field(String className, String name) {
        try {
            return type(className).getDeclaredField(name);
        } catch (NoSuchFieldException e) {
            fail(className + " needs a variable named " + name + ". Rename the variable that holds it.");
            return null;
        }
    }

    // An int argument arrives as an Integer, so its parameter type is turned back into int (and the same for double).
    // Any other object is looked up by its class, so pass an object of the exact parameter type.
    private static Class<?>[] typesOf(Object[] arguments) {
        Class<?>[] types = new Class<?>[arguments.length];
        for (int i = 0; i < arguments.length; i++) {
            Class<?> type = arguments[i].getClass();
            if (type == Integer.class) {
                type = int.class;
            } else if (type == Double.class) {
                type = double.class;
            }
            types[i] = type;
        }
        return types;
    }

    private static String names(Class<?>[] types) {
        return Arrays.stream(types).map(Class::getSimpleName).collect(Collectors.joining(", "));
    }

    private static String values(Object[] arguments) {
        return Arrays.stream(arguments).map(String::valueOf).collect(Collectors.joining(", "));
    }
}
