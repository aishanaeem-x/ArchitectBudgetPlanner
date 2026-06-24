package service;
import java.io.IOException;
import java.util.*;
import model.plan;
import filestorage.fileStorage;

public class PlanService {
    private List<plan> plans;
    private fileStorage storage;

    public PlanService(){
        storage= new fileStorage();
        try{
            plans= storage.loadPlans();

        }
        catch(IOException e){
            System.out.println("Error while loading plans");
        }
    }
    public void addPlan(plan p){
        plans.add(p);
        storage.savePlans(plans);
    }

    public List<plan> getPlansByClientId(String ClientID){
        List<plan> Clientplans= new ArrayList<>();
        for(plan p:plans){
            if(p.getClientId().equals(ClientID)){
                Clientplans.add(p);
            }
        }
        return Clientplans;
    }
    
}
