package org.aibles.userservice.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)   // nếu không có dòng này, id sẽ chỉ tự động tăng trong database mà không hiển thị id cho người dùng ??
    private int id;

    @NotBlank(message = "Name must not be blank")
    @Pattern(regexp = "[^0-9]+", message = "Name cannot contain numbers")
    @Size(min = 1, max =30, message = "Name must have atleast 1 characters")
    @Column(name = "name")
    private String name;

    @Min (value = 18, message = "Age must be more than 18")
    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
