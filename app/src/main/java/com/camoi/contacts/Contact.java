package com.camoi.contacts;

public class Contact {
    private String Name;
    private String Number;

    public Contact(String Name, String Number)
    {
        this.Name=Name;
        this.Number=Number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

}
