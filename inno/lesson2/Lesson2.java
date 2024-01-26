package inno.lesson2;

public class Lesson2 {
    public static void main(String[] args) throws AnnotationException {
        TestingClass testingClass = new TestingClass();
        TestRunner.runTests(testingClass);
    }
}
