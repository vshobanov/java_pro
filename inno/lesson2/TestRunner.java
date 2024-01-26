package inno.lesson2;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;

public class TestRunner {
    public static void runTests(TestingClass tcls) {
        Class cls = tcls.getClass();
        Method[] methods = cls.getMethods();
        if (testAnnotationValidator(cls) && suiteAnnotationValidator(cls)) {
            //System.out.println(Arrays.toString(methods));
            for (Method m : methods) {
                try {
                    if (m.isAnnotationPresent(BeforeSuite.class)) {
                        m.invoke(tcls);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
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
                                }
                                method.invoke(tcls, convertedParams);
                            } else method.invoke(tcls);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    });
            for (Method m : methods) {
                try {
                    if (m.isAnnotationPresent(AfterSuite.class)) {
                        m.invoke(tcls);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        } else throw new IllegalArgumentException("Exception with annotated methods");
    }

    public static int annotationsCounter(Class cls, Class annotation) {
        int count = 0;
        for (Method m : cls.getMethods()) {
            if (m.isAnnotationPresent(annotation)) count++;
        }
        return count;
    }

    public static boolean testAnnotationValidator(Class cls) {
        int min = 0, max = 10;
        Method[] methods = cls.getMethods();
        for (Method m : methods) {
            if (m.isAnnotationPresent(Test.class) && (m.getAnnotation(Test.class).priority() < min || m.getAnnotation(Test.class).priority() > max))
                return false;
        }
        return true;
    }

    public static boolean suiteAnnotationValidator(Class cls) {
        return !(annotationsCounter(cls, BeforeSuite.class) > 1 || annotationsCounter(cls, AfterSuite.class) > 1);
    }

}



