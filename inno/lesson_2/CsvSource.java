package inno.lesson_2;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CsvSource {
    String value() default " ";
}
