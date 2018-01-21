package com.masum.mcc;

/**
 * Created by masum on 1/18/2018.
 */

public class Employee {
    private int id;
    private String name;
    private String age;
    private String email;
    private String phone;
    private byte[] image;

    public Employee(int id, String name, String age, String email, String phone,byte[] image) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.image = image;
    }

    public Employee(String name, String age, String email, String phone,byte[] image) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.image = image;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
