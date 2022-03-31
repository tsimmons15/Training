package Generic;

public class GenericPlayground {
    public static void main(String[] args) {
        Box a = new Box();
        a.addThing(new Object());
        a.addThing(new Integer(1));
        a.addThing("A thing");
        System.out.println(a.getObject());

        Box numOne = new Box();
        numOne.addThing(2);
        Box numTwo = new Box();
        numTwo.addThing(3);
        //System.out.println(numOne.getObject() + numTwo.getObject());
        System.out.println(((Integer)numOne.getObject()) + ((Integer)numTwo.getObject()));

        GenericBox<Integer> bOne = new GenericBox<Integer>();
        bOne.addThing(2);
        GenericBox<Integer> bTwo = new GenericBox<>();
        bTwo.addThing(3);
        System.out.println(bOne.getThing() + bTwo.getThing());

    }
}

class Box {
    private Object thing;

    public Object getObject() {
        return thing;
    }

    public boolean isEmpty() {
        return thing == null;
    }

    public void addThing(Object thing) {
        if (!isEmpty()) {
            System.out.println("Already got a thing");
        } else {
            this.thing = thing;
        }
    }
}

class GenericBox<T> {
    private T thing;

    public T getThing() {
        return thing;
    }

    public boolean isEmpty() {
        return thing == null;
    }

    public void addThing(T thing) {
        if (!isEmpty()) {
            System.out.println("Already have a thing");
        } else {
            this.thing = thing;
        }
    }
}