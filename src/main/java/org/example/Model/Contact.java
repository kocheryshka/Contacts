package org.example.Model;

public class Contact {

    private final String name;

    private final String phone;

    private final String email;

    public Contact(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public static Contact parseContact(String string){
        String[] entity = string.split(";");
        if (entity.length != 3){
            throw new RuntimeException("Ошибка создания контакта");
        }
        return new Contact(entity[0], entity[1], entity[2]);
    }

    @Override
    public String toString() {
        return name+ ";" + phone + ";" + email;
    }
}
