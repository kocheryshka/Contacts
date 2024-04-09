package org.example.service;

import org.example.Model.Contact;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Component
@Profile("init")
public class DefaultLoader {
    private final ContactList contactList;

    @Value("${app.init_filepath}")
    private String filePath;

    public DefaultLoader(ContactList contactList) {
        this.contactList = contactList;
    }

    @PostConstruct
    public void loadContacts() {
        Resource resource = (Resource) new ClassPathResource(filePath);
        try{
            InputStream inputStream = resource.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            String result = FileCopyUtils.copyToString(reader);
            String[] strings = result.split("\n");
            for (String string: strings){
                Contact contact = Contact.parseContact(string);
                contactList.addContact(contact);
            }
        } catch(Exception e){
            System.out.println("Ошибка добавления контакта");
        }
    }

}
