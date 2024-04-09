package org.example.service;

import org.example.Model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class ContactList {

    @Value("${app.filepath}")
    private String filePath;

    private Map<String, Contact> contactList = new HashMap<>();


    public void addContact(Contact contact){
        if (contactList.containsKey(contact.getEmail())){
            throw new RuntimeException("Контакт с таким email уже существует");
        }
        contactList.put(contact.getEmail(), contact);
    }


    public void saveContact(){
        try(FileWriter writer = new FileWriter(filePath)) {
            for (Contact contact: contactList.values()) {
                writer.append(contact.toString()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения контактов в файл");
        }
    }

    public void deleteContact(String email){
        if (!contactList.containsKey(email)){
            throw new RuntimeException("Нет контакта с таким email");
        }
        contactList.remove(email);
    }

    public void showContacts() {
        for (Contact contact: contactList.values()) {
            System.out.println(contact);
        }
    }
}
