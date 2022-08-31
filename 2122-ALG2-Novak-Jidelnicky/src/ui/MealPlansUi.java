package ui;

import app.MealPlans;
import utils.MealPlansInterface;
import utils.TypeOfMealEnum;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * UI class
 *
 * @author petr.novak
 *
 */
public class MealPlansUi {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        MealPlansInterface appInterface = new MealPlans();
        appInterface.loadMealsFile();
        appInterface.loadMealPlansFile();

        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = myDateObj.format(myFormatObj);
        System.out.println("Jídelníčky by Petr Novák");
        System.out.println("Právě je "+time);
        System.out.println("");


        Boolean end = false;
        String choice = "";
        while(!end){
            System.out.println("Vyberte z možností zadáním jejího čísla");
            System.out.println("1) Vypsat jídelníčky");
            System.out.println("2) Vypsat jídla");
            System.out.println("3) Najít jídelníček");
            System.out.println("4) Najít jídlo");
            System.out.println("5) Přidat/odebrat jídelníčky/jídla");
            System.out.println("6) Ukončit program");
            choice = sc.next();

            if(choice.equalsIgnoreCase("1")){
                appInterface.printMealPlans();
            } else if (choice.equalsIgnoreCase("2")){
                appInterface.printMeals();
            } else if (choice.equalsIgnoreCase("3")){
                System.out.println("Napište hledané jídlo v jídelníčku");
                appInterface.findAndPrintMealPlan(sc.next());
            } else if (choice.equalsIgnoreCase("4")){
                System.out.println("Napište hledané jídlo v jídelníčku");
                appInterface.findAndPrintMeal(sc.next());
            } else if (choice.equalsIgnoreCase("5")){
                System.out.println("1) Přidat jídelníček");
                System.out.println("2) Přidat jídlo");
                System.out.println("3) Odebrat jídelníček");
                System.out.println("4) Odebrat jídlo");
                System.out.println("5) Zpět");
                choice = sc.next();
                if(choice.equalsIgnoreCase("1")){
                    System.out.println("Zadej název jídelníčku");
                    System.out.println("Z");
                    System.out.println("");
                    //appInterface.addMealPlan();
                }else if (choice.equalsIgnoreCase("2")){
                    System.out.println("Vyber druh jídla");
                    System.out.println("1) snidane");
                    System.out.println("2) obed");
                    System.out.println("3) vecere");
                    System.out.println("4) svacina");
                    TypeOfMealEnum tempType = TypeOfMealEnum.snack;
                    String tempName, tempNote;
                    int tempKcal, tempAmount;
                    choice = sc.next();
                    if(choice.equalsIgnoreCase("1")){
                        tempType = TypeOfMealEnum.breakfast;
                    }else if (choice.equalsIgnoreCase("2")){
                        tempType = TypeOfMealEnum.lunch;
                    }else if (choice.equalsIgnoreCase("3")){
                        tempType = TypeOfMealEnum.dinner;
                    }else if (choice.equalsIgnoreCase("4")){
                        tempType = TypeOfMealEnum.snack;
                    }
                    System.out.println("Zadej název jídla");
                    sc.nextLine();
                    tempName = sc.nextLine();
                    System.out.println("Zadej množství (v gramech)");
                    tempAmount = sc.nextInt();
                    System.out.println("Zadej kcal hodnotu jídla");
                    tempKcal = sc.nextInt();
                    System.out.println("Můžeš si přidat poznámku k jídlu");
                    sc.nextLine();
                    tempNote = sc.nextLine();
                    appInterface.addMeal(tempName, tempNote, tempKcal, tempAmount, tempType);
                }else if (choice.equalsIgnoreCase("3")){
                    System.out.println("Zadej název jídelníčku k odebrání");
                    sc.nextLine();
                    appInterface.removeMealPlan( sc.nextLine());
                }else if (choice.equalsIgnoreCase("4")){
                    System.out.println("Zadej název jídla k odebrání");
                    sc.nextLine();
                    appInterface.removeMeal(sc.nextLine());
                }else if (choice.equalsIgnoreCase("5")){
                    System.out.println("Navraceni do menu");
                }
            }else if (choice.equalsIgnoreCase("6")){
                System.out.println("Vypinani aplikace");
                end = true;
                appInterface.write();

            }

        }
    }
}
