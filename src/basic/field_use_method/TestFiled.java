package basic.field_use_method;

public class TestFiled {
    // 每一次都会获取新的时间 one-proxy
    long birth = System.currentTimeMillis();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            test();
        }
    }

    private static void test() throws InterruptedException{
        TestFiled testFiled = new TestFiled();
        System.out.println(testFiled.birth);

        Thread.sleep(100);
    }
}
