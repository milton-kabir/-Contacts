//package contacts;
package com.kabir.milton;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Contact {
    private String name;
    private String phoneNumber;
    private LocalDateTime created;
    private LocalDateTime edit;


    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.created = LocalDateTime.now();
        this.edit = LocalDateTime.now();
    }

    public String[] getAllEditableFields() {
        return new String[]{"name", "number", "created", "lastModified"};
    }


    public void setPhoneNumber(String phoneNumber) {
        if (validNumber(phoneNumber)) {
            this.phoneNumber = phoneNumber;
        } else {
            System.out.println("Wrong number format!");
            this.phoneNumber = "";
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        if (hasNumber()) {
            return phoneNumber;
        }
        return "[no number]";
    }

    private boolean validNumber(String number) {
        String text = "((\\+?(([a-zA-z0-9]+[- ][a-zA-z0-9]{2,})|([a-zA-z0-9]+[- ]\\([a-zA-z0-9]{2,}\\))|(\\([a-zA-z0-9]+\\)[- ][a-zA-z0-9]{2,})))|(\\+?)\\(?[a-zA-Z0-9]+\\)?)([- ][a-zA-z0-9]{2,})*";
        Pattern pattern = Pattern.compile(text);
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public boolean hasNumber() {
        return !"".equals(phoneNumber);
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getEdit() {
        return edit;
    }

    public void setEdit(LocalDateTime edit) {
        this.edit = edit;
    }

    public abstract String infoName();

    @Override
    public String toString() {
        return "Number: " + phoneNumber +
                "\nTime created: " + created +
                "\nTime last edit: " + edit;
    }

    public abstract void editField(String field, String value);

    public abstract String getField(String name);
}

class Person extends Contact {
    private String surname;
    private String birthDay;
    private String gender;

    public Person(String phoneNumber, String name, String surname, String birthDay, String gender) {
        super(name, phoneNumber);
        this.surname = surname;
        this.birthDay = birthDay;
        this.gender = gender;
    }

    public String[] getAllEditableFields() {
        return new String[]{"name", "surname", "birth", "gender", "number"};
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (gender.equals("F") || gender.equals("M")) {
            this.gender = gender;
        } else {
            this.gender = "[no date]";
            System.out.println("Bad gender!");
        }

    }

    @Override
    public String infoName() {
        return this.getName() + " " + this.getSurname();
    }

    @Override
    public String toString() {
        return "Name: " + getName() +
                "\nSurname: " + surname +
                "\nBirth date: " + (birthDay == null || birthDay.equals("") ? "[no data]" : birthDay) +
                "\nGender: " + (gender == null || gender.equals("") ? "[no data]" : gender) +
                '\n' + super.toString();
    }

    @Override
    public void editField(String field, String value) {
        if (field.equals("name")) {
            super.setName(value);
        } else if (field.equals("surname")) {
            setSurname(value);
        } else if (field.equals("birth")) {
            try {
                setBirthDay(value);
            } catch (DateTimeParseException e) {
                System.out.println("Bad birth date!");
            }
        } else if (field.equals("gender")) {
            setGender(value);
        } else if (field.equals("number")) {
            super.setPhoneNumber(value);
        }
    }

    @Override
    public String getField(String name) {
        if (name.equals("name")) {
            return super.getName();
        } else if (name.equals("number")) {
            return super.getPhoneNumber();
        } else if (name.equals("surname")) {
            return getSurname();
        } else if (name.equals("birth")) {
            return getBirthDay().toString();
        } else if (name.equals("gender")) {
            return getGender();
        } else {
            return null;
        }
    }
}

class Organization extends Contact {
    private String address;

    public Organization(String phoneNumber, String name, String address) {
        super(name, phoneNumber);
        this.address = address;
    }

    public String[] getAllEditableFields() {
        return new String[]{"name", "address", "number", "created", "lastModified"};
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String infoName() {
        return this.getName();
    }

    @Override
    public String toString() {
        return "Organization name: " + super.getName() +
                "\nAddress: " + address +
                '\n' + super.toString();
    }

    @Override
    public void editField(String field, String value) {
        if (field.equals("name")) {
            super.setName(value);
        } else if (field.equals("address")) {
            setAddress(value);
        } else if (field.equals("number")) {
            setPhoneNumber(value);
        }
    }

    @Override
    public String getField(String name) {
        if (name.equals("name")) {
            return super.getName();
        } else if (name.equals("address")) {
            return getAddress();
        } else if (name.equals("number")) {
            return super.getPhoneNumber();
        } else {
            return null;
        }
    }
}

class PhoneBook implements Serializable {
    private List<Contact> contactsList;
    private static final long serialVersionUID = 1L;

    public PhoneBook() {
        contactsList = new ArrayList<>();
    }

    public List<Contact> getContactsList() {
        return contactsList;
    }

    public void addPerson(String name, String surname, String phone, String birthDate, String gender) {
        Contact added = new Person(phone, name, surname, birthDate, gender);
        contactsList.add(added);
        System.out.println("The record added.\n");
    }

    public void addOrganization(String name, String phone, String address) {
        Contact added = new Organization(phone, name, address);
        contactsList.add(added);
        System.out.println("The record added.\n");
    }

    public void remove(int num) {
        if (num > 0 && num < contactsList.size()) {
            contactsList.remove(num);
            System.out.println("The record removed!");
        } else {
            System.out.println("Wrong record number!");
        }
    }


    public void list() {
        for (int i = 0; i < this.contactsList.size(); i++) {
            System.out.println(i + 1 + ". " + this.contactsList.get(i).infoName());
        }
    }

    public void editRecord(int num, Scanner scanner) {
        if (num > 0 && num < contactsList.size() + 1) {
            String[] fields = contactsList.get(num - 1).getAllEditableFields();
            System.out.print("Select a field (");
            for (String field : fields) {
                System.out.print(field + ", ");
            }
            System.out.print(")");
            String field = scanner.nextLine();
            if (Arrays.asList(fields).contains(field)) {
                if (field.equals("name")) {
                    System.out.println("Enter name: ");
                    Contact newContact = contactsList.get(num - 1);
                    newContact.editField("name", scanner.nextLine());
                    newContact.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newContact);
                } else if (field.equals("surname")) {
                    System.out.println("Enter surname: ");
                    Person newPerson = (Person) contactsList.get(num - 1);
                    newPerson.editField("surname", scanner.nextLine());
                    newPerson.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newPerson);
                } else if (field.equals("number")) {
                    System.out.println("Enter number: ");
                    Contact newContact = contactsList.get(num - 1);
                    newContact.editField("number", scanner.nextLine());
                    newContact.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newContact);
                } else if (field.equals("address")) {
                    System.out.println("Enter address: ");
                    Organization newOrganization = (Organization) contactsList.get(num - 1);
                    newOrganization.editField("address", scanner.nextLine());
                    newOrganization.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newOrganization);
                } else if (field.equals("birth")) {
                    System.out.println("Enter birth date: ");
                    Person newPerson = (Person) contactsList.get(num - 1);
                    newPerson.editField("birth", scanner.nextLine());
                    newPerson.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newPerson);
                } else if (field.equals("gender")) {
                    System.out.println("Enter gender: ");
                    Person newPerson = (Person) contactsList.get(num - 1);
                    newPerson.editField("gender", scanner.nextLine());
                    newPerson.setEdit(LocalDateTime.now());
                    contactsList.set(num - 1, newPerson);
                } else {
                    System.out.println("Wrong field name!");
                }
            } else {
                System.out.println("Wrong record number!");
            }
        }
    }

    public int count() {
        return contactsList.size();
    }

    public Contact get(int index) {
        return contactsList.get(index);
    }

}

public class Main implements Serializable {
    private static final long serialVersionUID = 1L;
    public static Scanner scanner = new Scanner(System.in);
    public static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("[menu] Enter action (add, list, search, count, exit):");
            String takeAction = scanner.nextLine();
            if (takeAction.equals("exit")) {
                return;
            } else {
                action(takeAction);
            }
        }
    }


    public static void recordMenu(int number) {
        System.out.println("[record] Enter action (edit, delete, menu):");
        String action = scanner.nextLine();
        if ("edit".equals(action)) {
            phoneBook.editRecord(number, scanner);
            System.out.println("Saved\n");
        } else if ("delete".equals(action)) {
            remove();
        } else if ("menu".equals(action)) {
            System.out.println();
            return;
        }
    }

    public static void listMenu() {
        System.out.println("[list] Enter action ([number], back):");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        if (action.equals("back")) {
            return;
        } else if (digits.matcher(action).find()) {
            int number = Integer.parseInt(action);
            if (number > 0 && number < phoneBook.count() + 1) {
                System.out.println(phoneBook.get(number - 1).toString());
            }
            System.out.println();
            recordMenu(number);
        } else {
            System.out.println("Unknown command!");
        }
    }


    public static void action(String takeAction) {
        if (takeAction.equals("add")) {
            add();
        } else if (takeAction.equals("list")) {
            list();
        } else if (takeAction.equals("search")) {
            search();
        } else if (takeAction.equals("count")) {
            count();
            System.out.println();
        }

    }

    private static void search() {
        System.out.println("Enter search query:");
        String query = scanner.nextLine();
        ArrayList<Integer> foundRecords = new ArrayList<>();
        for (int i = 0; i < phoneBook.count(); i++) {
            String[] editableFields = phoneBook.get(i).getAllEditableFields();
            String data = "";
            for (int j = 0; j < editableFields.length; j++) {
                data += phoneBook.get(i).getField(editableFields[j]);
            }
            Pattern dataQuery = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
            Matcher matcher = dataQuery.matcher(data);
            if (matcher.find()) {
                foundRecords.add(i);
            }
        }
        if (foundRecords.size() != 0) {
            System.out.println("Found " + foundRecords.size() + " results");
            for (int i : foundRecords) {
                if (phoneBook.get(i).getField("surname") != null) {
                    System.out.println(phoneBook.get(i).getField("name") + " " + phoneBook.get(i).getField("surname"));
                } else {
                    System.out.println(phoneBook.get(i).getField("name"));
                }
            }
        } else {
            System.out.println("Found 0 results");
        }
        System.out.println();

        System.out.println("[search] Enter action ([number], back, again):");
        String action = scanner.nextLine();
        Pattern digits = Pattern.compile("[0-9]+", Pattern.CASE_INSENSITIVE);
        if (action.equals("back")) {
            return;
        } else if (action.equals("again")) {
            search();
        } else if (digits.matcher(action).find()) {
            int index = foundRecords.get(Integer.parseInt(action) - 1);
            System.out.println(phoneBook.get(index).toString());
            recordMenu(index);
        } else {
            System.out.println("Unknown command!");
        }
    }

    private static void list() {
        phoneBook.list();
        System.out.println();
        listMenu();
    }

    private static void add() {
        System.out.println("Enter the type (person, organization):");
        String type = scanner.nextLine();
        if (type.equals("person")) {
            System.out.println("Enter the name:");
            String name = scanner.nextLine();
            System.out.println("Enter the surname:");
            String surname = scanner.nextLine();
            System.out.println("Enter the birth date:");
            String birthDate = scanner.nextLine();
            System.out.println("Enter the gender (M, F):");
            String gender = scanner.nextLine();
            System.out.println("Enter the number:");
            String phone = scanner.nextLine();
            phoneBook.addPerson(name, surname, phone, birthDate, gender);
        } else if (type.equals("organization")) {
            System.out.println("Enter the organization name:");
            String name = scanner.nextLine();
            System.out.println("Enter the address:");
            String address = scanner.nextLine();
            System.out.println("Enter the number:");
            String phone = scanner.nextLine();
            phoneBook.addOrganization(name, phone, address);
        } else {
            System.out.println("Wrong type!");
        }

    }

    public static void remove() {
        if (phoneBook.count() == 0) {
            System.out.println("No records to remove!\n");
        } else {
            phoneBook.list();
            System.out.println("Select a record:");
            int record = scanner.nextInt();
            String n = scanner.nextLine();
            phoneBook.remove(record - 1);
            System.out.println("The record removed!\n");
        }
    }

    public static void count() {
        System.out.println("The Phone Book has " + phoneBook.count() + " records.\n");
    }

    public static void edit() {
        if (phoneBook.count() > 0) {
            phoneBook.list();
            System.out.println("Select a record:");
            int number = Integer.parseInt(scanner.nextLine());
            phoneBook.editRecord(number, scanner);
            System.out.println("The record updated!");
        } else {
            System.out.println("No records to edit!");
        }
    }

}