package basic;
// 子类new时默认调用父类无参构造方法
// 可以手动调super(...)——须在第一行——代替默认的super()

public class TestParent {
    static {
        System.out.println("init");
    }
    private static class InnerClass{
        final static String xx = "xx";
        static {
            System.out.println("init inner");
        }
    }
    public static void main(String[] args) {
        new Child();
        Child jack = new Child("Jack");
        Child jj = new Child(jack, 1);
        Child jk = new Child(jj, 2);

        System.out.println(jack.getName());
        System.out.println(InnerClass.xx);
        System.out.println(jj);
        System.out.println(jk);
    }

}

class Child extends Parent{
    private String name;
    private int order = 0;

    public String getName() {
        return name;
    }

    public Child() {
        System.out.println("empty child");
    }
    public Child(String name) {
        this.name = name;
        System.out.println("1-arg child");
    }

    public Child(Child child, int order) {
        this(child.name);
        this.order = order;
    }

    @Override
    public String toString() {
        return "Child{" +
                "name='" + name + '\'' +
                ", order=" + order +
                '}';
    }
}

class Parent{
    private String name;

    public Parent() {
        System.out.println("parent no-arg constructor");
    }
    public Parent(String name) {
        System.out.println("parent 1-arg constructor");
        this.name = name;
    }
}
