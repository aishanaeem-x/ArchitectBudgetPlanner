package service;
import java.io.IOException;
import java.util.*;
import model.Client;
import filestorage.fileStorage;

public class ClientService{
   private List<Client> clients;
   private fileStorage storage;
   
   public ClientService(){
    storage= new fileStorage();
    try{
        clients= storage.loadClients();
      }
    catch(IOException e){
        System.out.println("Error in loading Cleints");
      }
   }

   public void addClient(String name, String contact){
    clients.add(new Client(name, contact));
    storage.saveClients(clients);
   }

   public Client findClient(String ClientID){
    for(Client c: clients){
        if(c.getClientID()== ClientID){
            return c;
        }
    }
    return null;
   }

   public List<Client> getAllClients(){
    return clients;
   }
}