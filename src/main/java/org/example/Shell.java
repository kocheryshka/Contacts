package org.example;

import org.example.Model.Contact;
import org.example.service.ContactList;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class Shell {

    private ContactList contactList;

    public Shell(ContactList contactList) {
        this.contactList = contactList;
    }

    public void start(){
        while (true){
            System.out.println("Введите команду (help - список команд):");
            String command = new Scanner(System.in).nextLine();
            if(command.equalsIgnoreCase("add")){
                try{
                    addContact();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (command.equalsIgnoreCase("delete")){
                try {
                    deleteContact();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (command.equalsIgnoreCase("save")){
                try{
                    saveContacts();
                } catch(Exception e){
                    System.out.println(e.getMessage());
                }
            } else if (command.equalsIgnoreCase("show")){
                showContacts();
            } else if (command.equalsIgnoreCase("exit")){
                break;
            } else if (command.equalsIgnoreCase("help")){
                showHelp();
            } else{
                System.out.println("Такой команды не существует");
                System.out.println("");
            }
        }
    }

    private void showContacts() {
        System.out.println("Список контактов:");
        contactList.showContacts();
    }

    private void showHelp() {
        System.out.println("add - добавить контакт");
        System.out.println("show - показать контакты");
        System.out.println("delete - удалить контакт");
        System.out.println("save - сохранить контакты в файл");
        System.out.println("exit - выйти из приложения");
        System.out.println("");
    }

    private void saveContacts() {
        contactList.saveContact();
        System.out.println("Контакты сохранены в файл");
        System.out.println("");
    }

    private void deleteContact() {
        System.out.println("Введите email удаляемого контакта:");
        String email = new Scanner(System.in).nextLine();
        contactList.deleteContact(email);
        System.out.println("Контакт удален");
        System.out.println("");
    }

    private void addContact() {
        System.out.println("Введите контакт в формате ФИО;Телефон;email:");
        String contactString = new Scanner(System.in).nextLine();
        contactList.addContact(Contact.parseContact(contactString));
        System.out.println("Контакт добавлен");
        System.out.println("");
    }
}
