package inno.lesson2;

import java.util.UUID;

public class TestingClass {
    private String description;
    private int param1;
    private int score;
    private boolean condition1;

    private UUID uuid;


    @BeforeSuite
    public void generateUuid() {
        uuid = UUID.randomUUID();
        System.out.println("Firs step. Setted UUID =" + uuid);
    }
    @Test(priority = 3)
    @CsvSource(value = "10,Java,0,true")
    public static void test3(int a, String b, int c, boolean d) {

        System.out.println("Executing Test with priority 3");
        System.out.println("It was executed with parameters: " + a + " " + b + " " + c + " " + d);
    }

    @Test(priority = 9)
    @CsvSource("10, 20")
    public static void test(int a, int b) {
        System.out.println("Executing Test with priority 9");
        System.out.println("It was executed with parameters: " + a + " " + b );
    }

    @Test(priority = 10)
    public static void test10() {
        System.out.println("Executing Test with priority 10");
    }
    //@Test(priority = 11)
    //public static void test11() {
    //    System.out.println("Executing Test with priority 11");
    //}
    @Test(priority = 1)
    public static void test1() {
        System.out.println("Executing Test with priority 1");
    }

    @Test
    @CsvSource(value = "17,Python,20,false")
    public static void test5(int a, String b, int c, boolean d) {
        System.out.println("Executing Test with default priority (5) ");
        System.out.println("It was executed with parametrs: " + a + " " + b + " " + c + " " + d);
    }

    @AfterSuite
    public static void afterTest() {
        System.out.println("Executing Tests finished.");
    }

    @Override
    public String toString() {
        return "TestingClass{" +
                "description='" + description + '\'' +
                ", param1=" + param1 +
                ", score=" + score +
                ", condition1=" + condition1 +
                ", uuid=" + uuid +
                '}';
    }
}
