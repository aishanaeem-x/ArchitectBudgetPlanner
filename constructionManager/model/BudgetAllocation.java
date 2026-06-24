package model;
import java.io.Serializable;

public class BudgetAllocation implements Serializable {
    private String categoryName;
    private double allocatedAmount;
    private double percentage;
    private String materialRecommendation;
    private String tier;
    public BudgetAllocation(String categoryName, double allocatedAmount, double percentage,
            String materialRecommendation, String tier) {
            this.categoryName = categoryName;
            this.allocatedAmount = allocatedAmount;
            this.percentage = percentage;
            this.materialRecommendation = materialRecommendation;
            this.tier = tier;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public double getAllocatedAmount() {
        return allocatedAmount;
    }
    public double getPercentage() {
        return percentage;
    }
    public String getMaterialRecommendation() {
        return materialRecommendation;
    }
    public String getTier() {
        return tier;
    }
    
    @Override
    public String toString(){
        return categoryName+ " :Rs. "+ allocatedAmount;
    }
    
}
