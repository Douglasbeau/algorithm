package basic;

import bag.BagSolution;

import java.io.Serializable;

// 第一，short += 1才对，short = short + 1是错误的
// 第二，clone()需要重写（继承Cloneable）才能深度clone
// 也可以实现Serializable
public class AddSelf implements Cloneable, Serializable {
    BagSolution bag;

    public AddSelf() {
    }

    public AddSelf(BagSolution bag) {
        this.bag = bag;
    }

    @Override
    public String toString() {
        return "AddSelf{" +
                "Bag=" + bag +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws Exception {
        short a = 1;
        // a = a + 1 是错的，不能将int赋值给short
        a += 1;
        System.out.println(a);

        AddSelf addSelf = new AddSelf(new BagSolution());
        System.out.println(addSelf);
        AddSelf addSelf1 = (AddSelf)addSelf.clone();
        System.out.println(addSelf1);
        // false
        System.out.printf("addSelf == addSelf1 %s\n", addSelf == addSelf1);
        // true
        System.out.printf("addSelf.bag, addSelf1.bag %s\n", addSelf.bag == addSelf1.bag);
    }

}
