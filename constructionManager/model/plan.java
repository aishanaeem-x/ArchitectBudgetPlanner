package model;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
public class plan implements Serializable {
    private String planId;
    private String clientId;
    private String clientName;
    private String date;
    private double totalBudget;
    private String constructionType;
    private String tier;
    private List<BudgetAllocation> allocations;
    private String verdict;
    
    public plan(String clientId, String clientName, String date, double totalBudget, String constructionType,String tier, List<BudgetAllocation> allocations, String verdict) {
        planId= UUID.randomUUID().toString();
        this.clientId = clientId;
        this.clientName = clientName;
        this.date = date;
        this.totalBudget = totalBudget;
        this.constructionType = constructionType;
        this.tier = tier;
        this.allocations = allocations;
        this.verdict = verdict;
    }

    public String getPlanId() {
        return planId;
    }

    public String getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getDate() {
        return date;
    }

    public double getTotalBudget() {
        return totalBudget;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public String getTier() {
        return tier;
    }

    public List<BudgetAllocation> getAllocations() {
        return allocations;
    }

    public String getVerdict() {
        return verdict;
    }
    
    @Override
    public String toString(){
      return clientName+ "-Rs. "+ totalBudget;
    }
    
}
