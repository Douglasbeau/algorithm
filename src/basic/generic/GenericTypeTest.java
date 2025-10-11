package basic.generic;

public class GenericTypeTest {

    public static void main(String[] args) {
        PetCare<Pet> petCare = new PetCare<>();
        petCare.cleanPet(new BlackDog());
        petCare.cleanPet(new YellowDog());
        petCare.cleanPet(new Dog());

        PetCare<? super Dog> petCareS = new PetCare<Pet>();
        petCareS.set(new YellowDog());

    }

    private void populateWithDog(PetCare<? super Dog> list) {
        list.set(new YellowDog());
    }
}

class PetCare<T extends Pet> {

    public void cleanPet(T pet) {
        System.out.printf("cleaning %s\n", pet.name());
    }

    public void set(T yellowDog) {
        Movable dog = () -> {
            System.out.println(yellowDog.name() + " run");
        };
        Movable dog2 = new Movable() {
            @Override
            public void howMove() {
                return;
            }
        };
    }
}

abstract class Pet {
    abstract String name();
}
class Dog extends Pet{
    String name() {
        return "Dog";
    }
}

class YellowDog extends Dog{
    String name() {
        return "Yellow dog";
    }
}
class BlackDog extends Dog{
    @Override
    String name() {
        return "Black dog";
    }
}


@FunctionalInterface
interface Movable{
    void howMove();
}
