package filestorage;
import java.io.*;
import java.util.*;
import model.Client;
import model.plan;

public class fileStorage {
    private static final String BASE_DIR = System.getProperty("user.home") + "/ArchitectBudgetPlannerData/";
    private static final String CLIENTS_FILE = BASE_DIR + "clients.dat";
    private static final String PLANS_FILE   = BASE_DIR + "plans.dat";
    
    public fileStorage() {
    new java.io.File(BASE_DIR).mkdirs();
    }
    public void saveClients(List<Client> clients){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(CLIENTS_FILE))){
            oos.writeObject(clients);

        } catch(IOException e){
            System.out.println("Error Saving Clients");
        }
    }

    public void savePlans(List<plan> plans){
        try(ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(PLANS_FILE))){
            oos.writeObject(plans);

        } catch(IOException e){
            System.out.println("Error Saving Plans");
        }
    }
    @SuppressWarnings("unchecked")
    public List<Client> loadClients() throws IOException{
        try(ObjectInputStream ois= new ObjectInputStream(new FileInputStream(CLIENTS_FILE))){
            return (List<Client>) ois.readObject();
        }
        catch(FileNotFoundException e){
            return new ArrayList<>();
        }
        catch(IOException|ClassNotFoundException e){
            System.out.println("error loading clients");
            return new ArrayList<>();
        }
    }
    @SuppressWarnings("unchecked")
    public List<plan> loadPlans() throws IOException{
        try(ObjectInputStream ois= new ObjectInputStream(new FileInputStream(PLANS_FILE))){
            return (List<plan>) ois.readObject();
        }
        catch(FileNotFoundException e){
            return new ArrayList<>();
        }
        catch(IOException|ClassNotFoundException e){
            System.out.println("error loading plans");
            return new ArrayList<>();
        }
    }
}
