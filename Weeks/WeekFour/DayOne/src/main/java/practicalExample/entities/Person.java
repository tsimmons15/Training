package practicalExample.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
public class Person {
    private int id;
    private String fname;
    private String lname;
    private int age;

    @Override
    public String toString() {
        return "Person " + id + ": Called " + (fname + " " + lname) + ", aged " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && age == person.age && Objects.equals(fname, person.fname) && Objects.equals(lname, person.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname, age);
    }
}
