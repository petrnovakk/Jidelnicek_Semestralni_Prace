package app;

import utils.ExceptionFileNotFound;
import utils.ExceptionInputOutput;
import utils.MealPlansInterface;
import utils.TypeOfMealEnum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Class with the logic of the app
 *
 * @author petr.novak
 *
 */
public class MealPlans implements MealPlansInterface {

    private static final File fileMeals = new File("./src/srcData/Meals.csv");
    private static final File fileMealPlans = new File("./src/srcData/MealPlans.csv");

    private List<MealPlan> listMealPlans;
    private List<Meals> listMeals;

    /**
     * Constructor
     */
    public MealPlans(){
        listMealPlans = new ArrayList<>();
        listMeals = new ArrayList<>();
    }

    /**
     * Method for adding meal to the list
     */
    public void addMeal(String name, String note, int kcal, int amount, TypeOfMealEnum typeOfMealEnum){
        listMeals.add(new Meals(name, note, kcal, amount, typeOfMealEnum));
    }
    /**
     * Method for adding meal plan to the list
     */
    public void addMealPlan(String name, Meals breakfast, Meals lunch, Meals dinner, Meals snack, String note){
        listMealPlans.add(new MealPlan(name, breakfast, lunch, dinner, snack, note));
    }

    /**
     * Method for removing meal of the list
     */
    public void removeMeal(String name){
        listMeals.remove(findMeal(name));
    }
    /**
     * Method for removing meal plan of the list
     */
    public void removeMealPlan(String name){
        listMealPlans.remove(findMealPlan(name));
    }

    /**
     * Method for adjusting meal informations
     */
    public void adjustMeal(String name, String note, int kcal, int amount, TypeOfMealEnum typeOfMealEnum){
        listMeals.remove(findMeal(name));
        listMeals.add(new Meals(name, note, kcal, amount, typeOfMealEnum));
    }
    /**
     * Method for adjusting meal plan informations
     */
    public void adjustMealPlan(String name, Meals breakfast, Meals lunch, Meals dinner, Meals snack, String note){
        listMealPlans.remove(findMealPlan(name));
        listMealPlans.add(new MealPlan(name, breakfast, lunch, dinner, snack, note));
    }

    /**
     * Method for printing every single meal plan
     */
    public void printMealPlans(){
        for(MealPlan mealPlan : listMealPlans) {
            System.out.println(printMealPlan(mealPlan));
        }
    }
    /**
     * Method for finding meal plans by the parts of its name
     */
    public void findAndPrintMealPlan(String name){
        int temp = 0;
        for(MealPlan mealPlan : listMealPlans) {
            for (Meals meal : listMeals) {
                if ((meal.getName()).contains(name)) {
                    System.out.println(printMealPlan(listMealPlans.get(temp)));
                    break;
                }

            }
            temp++;
        }

    }
    /**
     * Method for finding meals by the parts of its name
     */
    public void findAndPrintMeal(String name){
        int temp = 0;
            for (Meals meal : listMeals) {
                if ((meal.getName()).contains(name)) {
                    System.out.println(printMeal(listMeals.get(temp)));
                }
                temp++;
            }

    }

    /**
     * Method for preparing string for printing meal plans
     */
    public String printMealPlan(MealPlan mealPlan){
        StringBuilder str = new StringBuilder();
        str.append(mealPlan.getName()+": \n");
        str.append("snidane: "+mealPlan.getBreakfast().getName()+", \n");
        str.append("obed: "+mealPlan.getLunch().getName()+", \n");
        str.append("vecere: "+mealPlan.getDinner().getName()+", \n");
        str.append("svacina: "+mealPlan.getSnack().getName()+", \n");
        str.append("celkem "+mealPlan.getTotalKcal()+"kcal, \n");
        str.append("poznamka: "+mealPlan.getNote()+"\n");
        return str.toString();
    }

    /**
     * Method for preparing string for printing meals
     */
    public String printMeal(Meals meal) {
        StringBuilder str = new StringBuilder();
        str.append(meal.getName()+": \n");
        str.append(meal.getAmount()+"g, \n");
        str.append(meal.getKcal()+"kcal, \n");
        str.append(meal.getNote()+"\n");
        return str.toString();
    }

    /**
     * Method for printing every single meal
     */
    public void printMeals(){
        for(Meals meals : listMeals) {
            System.out.println(printMeal(meals));
        }
    }

    /**
     * Method for finding index of meal exactly by its name
     */
    private int findMeal(String name){
        int temp = 0;
        for(Meals meal : listMeals){
            if((meal.getName()).equalsIgnoreCase(name)){
                break;
            }
            temp++;
        }
        return temp;
    }

    /**
     * Method for finding index of meal plan exactly by its name
     */
    private int findMealPlan(String name){
        int temp = 0;
        for(MealPlan mealPlan : listMealPlans){
            if((mealPlan.getName()).equalsIgnoreCase(name)){
                break;
            }
            temp++;
        }
        return temp;
    }

    /**
     * Method for loading of meals file
     */
    public void loadMealsFile(){
        String temp;
        String[] split;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileMeals))) {
            String name, note;
            int kcal, amount;
            TypeOfMealEnum typeOfMealEnum;

            while ((temp = reader.readLine()) != null) {
                split = temp.split(",");

                switch(split[0]) {
                    case "breakfast":
                        typeOfMealEnum = TypeOfMealEnum.breakfast;
                        break;
                    case "lunch":
                        typeOfMealEnum = TypeOfMealEnum.lunch;
                        break;
                    case "dinner":
                        typeOfMealEnum = TypeOfMealEnum.dinner;
                        break;
                    case "snack":
                        typeOfMealEnum = TypeOfMealEnum.snack;
                        break;
                    default:
                        throw new IllegalStateException("Nečekaná hodnota " + split[0]);
                }
                name = split[1];
                amount = Integer.parseInt(split[2]);
                kcal = Integer.parseInt(split[3]);
                note = split[4];

                listMeals.add(new Meals(name, note, kcal, amount, typeOfMealEnum));
            }
        } catch (FileNotFoundException e) {
            throw new ExceptionFileNotFound("Soubor nebyl nalezen");
        } catch (IOException e) {
            throw new ExceptionInputOutput("Spatne zadany vstup");
        }
    }

    /**
     * Method for loading of meal plans file
     */
    public void loadMealPlansFile(){
        String temp;
        String[] split;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileMealPlans))) {
            String name, note;
            int breakfastIndex, lunchIndex, dinnerIndex, snackIndex;
            while ((temp = reader.readLine()) != null) {
                split = temp.split(",");

                name = split[0];
                breakfastIndex = findMeal(split[1]);
                lunchIndex = findMeal( split[2]);
                dinnerIndex = findMeal(split[3]);
                snackIndex = findMeal(split[4]);
                note = split[5];
                listMealPlans.add(new MealPlan(name, listMeals.get(breakfastIndex), listMeals.get(lunchIndex), listMeals.get(dinnerIndex), listMeals.get(snackIndex), note));
            }
        } catch (FileNotFoundException e) {
            throw new ExceptionFileNotFound("Soubor nebyl nalezen");
        } catch (IOException e) {
            throw new ExceptionInputOutput("Spatne zadany vstup");
        }
    }

    public void listOfMeals(File listOfAllMeals) throws IOException{
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(listOfAllMeals)))){
            for (Meals meal : listMeals) {
                writer.println(meal.getName()+": ");
                writer.println(meal.getAmount()+"g, ");
                writer.println(meal.getKcal()+"kcal, ");
                writer.println(meal.getNote());
                writer.println();

            }
            for(MealPlan mealPlan : listMealPlans){
                writer.println(mealPlan.getName()+": ");
                writer.println("snidane: "+mealPlan.getBreakfast().getName()+", ");
                writer.println("obed: "+mealPlan.getLunch().getName()+", ");
                writer.println("vecere: "+mealPlan.getDinner().getName()+", ");
                writer.println("svacina: "+mealPlan.getSnack().getName()+", ");
                writer.println("celkem "+mealPlan.getTotalKcal()+"kcal, ");
                writer.println("poznamka: "+mealPlan.getNote());
                writer.println();
            }
        }catch (FileNotFoundException e) {
            throw new ExceptionFileNotFound("Zadaný soubor nebyl nalezen!");
        }
    }
    public void saveToBinary(File binListOfAll) throws FileNotFoundException, IOException{
        try(DataOutputStream out = new DataOutputStream(new FileOutputStream(binListOfAll, true))){
            out.writeInt(listMeals.size());
            for (Meals meal : listMeals) {
                out.writeUTF(meal.getName()+": ");
                out.writeUTF(meal.getAmount()+"g, ");
                out.writeUTF(meal.getKcal()+"kcal, ");
                out.writeUTF(meal.getNote());

            }
            out.writeInt(listMealPlans.size());
            for(MealPlan mealPlan : listMealPlans){
                out.writeUTF(mealPlan.getName()+": ");
                out.writeUTF("snidane: "+mealPlan.getBreakfast().getName()+", ");
                out.writeUTF("obed: "+mealPlan.getLunch().getName()+", ");
                out.writeUTF("vecere: "+mealPlan.getDinner().getName()+", ");
                out.writeUTF("svacina: "+mealPlan.getSnack().getName()+", ");
                out.writeUTF("celkem "+mealPlan.getTotalKcal()+"kcal, ");
            }

        }
    }

    public void write() throws IOException {
        listOfMeals(new File("./src/data/listOfAllMeals.csv"));
        saveToBinary(new File("./src/data/binListOfAll.csv"));
    }


    /**public void saveToPDF(File result) throws FileNotFoundException, DocumentException{
        var doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("mealList.pdf"));
        doc.open();
        var bold = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
        var paragraph = new Paragraph("list of meals");

        var table = new PdfTable();
        Stream.of()


     }*/
}
