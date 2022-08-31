package utils;

import app.MealPlan;
import app.Meals;

import java.io.IOException;

/**
 * Interface for MealPlans
 *
 * @author petr.novak
 */
public interface MealPlansInterface {
    public void loadMealsFile();
    public void loadMealPlansFile();
    public void printMealPlans();
    public void printMeals();
    public String printMeal(Meals meal);
    public void findAndPrintMealPlan(String name);
    public void findAndPrintMeal(String name);
    public void addMeal(String name, String note, int kcal, int amount, TypeOfMealEnum typeOfMealEnum);
    public void addMealPlan(String name, Meals breakfast, Meals lunch, Meals dinner, Meals snack, String note);
    public void removeMeal(String name);
    public void removeMealPlan(String name);
    public void adjustMeal(String name, String note, int kcal, int amount, TypeOfMealEnum typeOfMealEnum);
    public void adjustMealPlan(String name, Meals breakfast, Meals lunch, Meals dinner, Meals snack, String note);
    public void write() throws IOException;
}
