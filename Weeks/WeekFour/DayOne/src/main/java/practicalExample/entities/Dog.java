package practicalExample.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Dog {
    @NonNull
    private String name;
    private String owner;
    private int age;

}
