package service;
import java.util.*;
import model.*;

public class BudgetCalculator {
    //main method to bind everthing together
    public plan calculate(Client client, double budget, String ConstructionType, int bedrooms,int bathrooms){
        String tier= detremineTier(budget);
        List<BudgetAllocation> list= calculateAllocations(budget, tier);
        String feasibility= checkFeasibility(tier, bedrooms, bathrooms);
        String verdict= getVerdict(tier,bedrooms,bathrooms);
        String finalVerdict = feasibility + " " + verdict;
        String date=java.time.LocalDate.now().toString();
        plan p= new plan(client.getClientID(),client.getName(),date,budget,ConstructionType,tier,list,finalVerdict);
        return p;
    }

    public String detremineTier(double budget){
        if(budget<5000000)
            return("Basic");
        else if(budget>=5000000 && budget<15000000)
            return("Standard");
        else return("Premium");

    }

    public List<BudgetAllocation> calculateAllocations(double budget, String tier) {
    List<BudgetAllocation> Result = new ArrayList<>();

    double foundationAmount = (15 / 100.0) * budget;
    String foundationMaterial = getMaterialRecommendation("Foundation", tier);
    BudgetAllocation foundation = new BudgetAllocation("Foundation", foundationAmount, 15, foundationMaterial, tier);
    Result.add(foundation);

    double structureAmount = (25 / 100.0) * budget;
    String structureMaterial = getMaterialRecommendation("Structure and Walls", tier);
    BudgetAllocation structure = new BudgetAllocation("Structure and Walls", structureAmount, 25, structureMaterial, tier);
    Result.add(structure);

    double roofingAmount = (10 / 100.0) * budget;
    String roofingMaterial = getMaterialRecommendation("Roofing", tier);
    BudgetAllocation roofing = new BudgetAllocation("Roofing", roofingAmount, 10, roofingMaterial, tier);
    Result.add(roofing);

    double flooringAmount = (10 / 100.0) * budget;
    String flooringMaterial = getMaterialRecommendation("Flooring", tier);
    BudgetAllocation flooring = new BudgetAllocation("Flooring", flooringAmount, 10, flooringMaterial, tier);
    Result.add(flooring);

    double electricalAmount = (8 / 100.0) * budget;
    String electricalMaterial = getMaterialRecommendation("Electrical", tier);
    BudgetAllocation electrical = new BudgetAllocation("Electrical", electricalAmount, 8, electricalMaterial, tier);
    Result.add(electrical);

    double plumbingAmount = (8 / 100.0) * budget;
    String plumbingMaterial = getMaterialRecommendation("Plumbing", tier);
    BudgetAllocation plumbing = new BudgetAllocation("Plumbing", plumbingAmount, 8, plumbingMaterial, tier);
    Result.add(plumbing);

    double finishingAmount = (7 / 100.0) * budget;
    String finishingMaterial = getMaterialRecommendation("Finishing and Paint", tier);
    BudgetAllocation finishing = new BudgetAllocation("Finishing and Paint", finishingAmount, 7, finishingMaterial, tier);
    Result.add(finishing);

    double doorsAmount = (9 / 100.0) * budget;
    String doorsMaterial = getMaterialRecommendation("Doors and Windows", tier);
    BudgetAllocation doors = new BudgetAllocation("Doors and Windows", doorsAmount, 9, doorsMaterial, tier);
    Result.add(doors);

    double contingencyAmount = (8 / 100.0) * budget;
    String contingencyMaterial = getMaterialRecommendation("Contingency Fund", tier);
    BudgetAllocation contingency = new BudgetAllocation("Contingency Fund", contingencyAmount, 8, contingencyMaterial, tier);
    Result.add(contingency);

    return Result;
    }
    

    public String getMaterialRecommendation(String category, String tier){
        switch (category) {
           case "Foundation":
                 switch (tier) {
                     case "Basic": return "Low grade cement";
                     case "Standard": return "Standard grade cement";
                     case "Premium": return "High strength cement";
                     default: return "Unknown tier";
                    }
            case "Structure and Walls":
                switch(tier){
                    case "Basic": return "Hollow Block";
                    case "Standard": return "Standrad Brick";
                    case "Premium": return "Engineering Brick";
                    default: return "Unknown tier";
                   }
            case "Roofing":
                 switch (tier) {
                     case "Basic": return "Tin Roof";
                     case "Standard": return "Conventional RCC slab";
                     case "Premium": return "Insulated RCC Slab";
                     default: return "Unknown tier";
                    }
            case "Flooring":
                 switch (tier) {
                     case "Basic": return "Plain Concrete";
                     case "Standard": return "Ceramic tiles";
                     case "Premium": return "Marble/granite";
                     default: return "Unknown tier";
                    }              
            case "Electrical":
                 switch (tier) {
                     case "Basic": return "basic wiring, no MCBs";
                     case "Standard": return "Standard wiring with MCBs";
                     case "Premium": return "Concealed wiring with smart switches";
                     default: return "Unknown tier";
                    }
            case "Plumbing":
                 switch (tier) {
                     case "Basic": return "PVC pipes";
                     case "Standard": return "CPVC pipes";
                     case "Premium": return "Copper pipes";
                     default: return "Unknown tier";
                    }
            case "Finishing and Paint":
                 switch (tier) {
                     case "Basic": return "Distemper paint";
                     case "Standard": return "Emulsion paint";
                     case "Premium": return "Premium textured paint";
                     default: return "Unknown tier";
                    }
            case "Doors and Windows":
                 switch (tier) {
                     case "Basic": return "Iron frame";
                     case "Standard": return "Wooden frame, single panel";
                     case "Premium": return "Hardwood, double panel";
                     default: return "Unknown tier";
                    }
            case "Contingency Fund":
                 switch (tier) {
                     case "Basic": return "N/A";
                     case "Standard": return "N/A";
                     case "Premium": return "N/A";
                     default: return "Unknown tier";
                    }
            default: return("Error, give a category");
        }
    }

    public String checkFeasibility(String tier, int bedrooms, int bathrooms){
        int maxBedrooms=0, maxBathrooms=0;
        if(tier.equals("Basic")){
            maxBathrooms=2; maxBedrooms=2;
        }
        else if(tier.equals("Standard")){
            maxBathrooms=3; maxBedrooms=4;
        }
        else if(tier.equals("Premium")){
            maxBathrooms=4; maxBedrooms=5;
        }
        
        if(bedrooms<=maxBedrooms && bathrooms<=maxBathrooms){
            return "Your budget is sufficient for the requested " + bedrooms + " bedroom, " + bathrooms + " bathroom house.";
        }
        else 
            return "Your budget may not be sufficient for " + bedrooms + " bedrooms and " + bathrooms + " bathrooms at the " + tier + " tier. Consider increasing your budget or reducing room count.";
        
    }

    public String getVerdict(String tier, int bedrooms, int bathrooms){
        if(tier.equals("Basic")){
            return "Your budget is tight, suitable for a small " + bedrooms + " bedroom house with basic materials.";
        }
        else if(tier.equals("Standard")){
            return  "Your budget is sufficient for a standard " + bedrooms + " bedroom house with mid range materials.";
        }
        else if(tier.equals("Premium")){
            return "Your budget allows for a spacious " + bedrooms + " bedroom house with high end materials and finishes.";
        }
        return "Unable to generate Verdict";
    }

}
