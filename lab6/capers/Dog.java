package capers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import static capers.Utils.*;

/** Represents a dog that can be serialized.
 * @author TODO
*/
public class Dog implements Serializable { // TODO

    /** Folder that dogs live in. */
    static final File DOG_FOLDER = join(System.getProperty("user.dir"), ".capers/dogs");


    /** Age of dog. */
    private int age;
    /** Breed of dog. */
    private String breed;
    /** Name of dog. */
    private String name;

    /**
     * Creates a dog object with the specified parameters.
     * @param name Name of dog
     * @param breed Breed of dog
     * @param age Age of dog
     */
    public Dog(String name, String breed, int age) {
        this.age = age;
        this.breed = breed;
        this.name = name;
    }

    /**
     * Reads in and deserializes a dog from a file with name NAME in DOG_FOLDER.
     *
     * @param name Name of dog to load
     * @return Dog read from file
     */
    public static Dog fromFile(String name) {
        File dogFile = join(DOG_FOLDER, name);
        if (!dogFile.exists()) {
            return null;
        }
        return readObject(dogFile, Dog.class);
    }

    /**
     * Increases a dog's age and celebrates!
     */
    public void haveBirthday() {
        age += 1;
        System.out.println(this);
        System.out.println("Happy birthday! Woof! Woof!");
        File dogFile = join(DOG_FOLDER, name);
        writeObject(dogFile, this);
    }

    /**
     * Saves a dog to a file for future use.
     */
    public void saveDog() throws IOException {
        Dog dog = fromFile(name);
        if (dog != null) {
            return;
        }
        if (!DOG_FOLDER.exists()) {
            DOG_FOLDER.mkdir();
        }
        File dogFile = join(DOG_FOLDER, name);
        dogFile.createNewFile();
        writeObject(dogFile, this);
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format(
            "Woof! My name is %s and I am a %s! I am %d years old! Woof!",
            name, breed, age);
    }

}
