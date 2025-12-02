package basic.design;

// 静态内部类
public class Singleton {
    private static class Inner {
        private static final Singleton singleton = new Singleton();
    }
    public static Singleton getInstance() {
        return Inner.singleton;
    }
    public void say() {
        System.out.println(this.hashCode());
    }

    public static void main(String[] args) {
        Singleton s1 = Singleton.getInstance();
        s1.say();
        Singleton.getInstance().say();
    }
}
