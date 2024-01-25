package inno.lesson_2;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

public class TestRunner {
    public static String runTests(TestingClass tcls) {
        Class cls = tcls.getClass();
        Method[] methods = cls.getMethods();
        //System.out.println(Arrays.toString(methods));
        for (Method m : methods) {
            if (AnnotationsCounter(cls, BeforeSuite.class) > 1 || AnnotationsCounter(cls, AfterSuite.class) > 1)
                throw new IllegalArgumentException("Wrong number of annotated methods");
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
                    TestAnnotationValidator(method);
                    if (TestAnnotationValidator(method)) {
                        try {
                            CsvSource csvSource = method.getAnnotation(CsvSource.class);
                            if (csvSource != null) {
                                String csvData = csvSource.value();
                                String[] data = csvData.split(",");
                                int a = Integer.parseInt(data[0].trim());
                                String b = (data[1].trim());
                                int c = Integer.parseInt(data[2].trim());
                                boolean d = Boolean.parseBoolean(data[3].trim());
                                method.invoke(tcls, a, b, c, d);
                            } else method.invoke(tcls);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    } else throw new IllegalArgumentException("Priority must be set in interval 0-10");
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
        return tcls.toString();

    }

    public static int AnnotationsCounter(Class cls, Class annotation) {
        int count = 0;
        for (Method m : cls.getMethods()) {
            if (m.isAnnotationPresent(annotation)) count++;
        }
        return count;
    }

    public static boolean TestAnnotationValidator(Method method) {
        int min = 0, max = 10;
        return method.getAnnotation(Test.class).priority() >= min && method.getAnnotation(Test.class).priority() <= max;
    }

}

