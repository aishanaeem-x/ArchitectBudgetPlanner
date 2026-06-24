package model;
import java.io.Serializable;
import java.util.UUID;

public class Client implements Serializable{
    private String ClientID;
    private String name;
    private String contact;

    public Client( String name, String contact) {
        ClientID = UUID.randomUUID().toString();
        this.name = name;
        this.contact = contact;
    }

    public String getClientID() {
        return ClientID;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString(){
        return name;
    } 

}