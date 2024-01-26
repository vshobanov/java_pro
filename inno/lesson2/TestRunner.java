package inno.lesson2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;

public class TestRunner {
    public static void runTests(TestingClass tcls) throws AnnotationException {
        Class cls = tcls.getClass();
        Method[] methods = cls.getMethods();
        if (testAnnotationValidator(methods) && suiteAnnotationValidator(methods)) {
            for (Method m : methods) {
                try {
                    if (m.isAnnotationPresent(BeforeSuite.class)) {
                        m.invoke(tcls);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            testExecutor(tcls,methods);
            for (Method m : methods) {
                try {
                    if (m.isAnnotationPresent(AfterSuite.class)) {
                        m.invoke(tcls);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else throw new AnnotationException("Exception with annotated methods");
    }

    public static int annotationsCounter(Method[] methods, Class annotation) {
        int count = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(annotation)) count++;
        }
        return count;
    }

    public static boolean testAnnotationValidator(Method[] methods) {
        int min = 0, max = 10;
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class) && (m.getAnnotation(Test.class).priority() < min || m.getAnnotation(Test.class).priority() > max))
                return false;
        }
        return true;
    }

    public static boolean suiteAnnotationValidator(Method[] methods) {
        return !(annotationsCounter(methods, BeforeSuite.class) > 1 || annotationsCounter(methods, AfterSuite.class) > 1);
    }

    public static void testExecutor(TestingClass tcls ,Method[] methods) throws AnnotationException {
        Arrays.stream(methods)
                .filter(m -> m.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparingInt(m -> m.getAnnotation(Test.class).priority()))
                .forEach(method -> {
                    try {
                        CsvSource csvSource = method.getAnnotation(CsvSource.class);
                        if (csvSource != null) {
                            String csvData = csvSource.value();
                            String[] data = csvData.split(",");
                            Type[] parameterTypes = method.getParameterTypes();
                            Object[] convertedParams = new Object[data.length];
                            if (method.getParameterCount() == data.length) {
                                for (int i = 0; i < data.length; i++) {
                                    if (parameterTypes[i] == int.class) {
                                        convertedParams[i] = Integer.parseInt(data[i].trim());
                                    } else if (parameterTypes[i] == String.class) {
                                        convertedParams[i] = data[i].trim();
                                    } else if (parameterTypes[i] == boolean.class) {
                                        convertedParams[i] = Boolean.parseBoolean(data[i].trim());
                                    }
                                }
                            } else throw new AnnotationException("Incorrect methods marking (number of parameters)");
                            method.invoke(tcls, convertedParams);
                        } else method.invoke(tcls);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }

}



