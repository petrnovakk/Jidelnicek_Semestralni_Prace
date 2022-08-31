package app;

import utils.TypeOfMealEnum;

import java.lang.reflect.Type;
/**
 * Class of Meals object
 *
 * @author petr.novak
 */
public class Meals {

    private TypeOfMealEnum typeOfMealEnum;
    private String name;
    private String note;
    private int kcal;
    private int amount;

    /**
     * Method for getting name of the meal
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Method for getting note of the meal
     *
     * @return note
     */
    public String getNote() {
        return note;
    }
    /**
     * Method for getting kcal of the meal
     *
     * @return kcal
     */
    public int getKcal() {
        return kcal;
    }
    /**
     * Method for getting amount of the meal
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }
    /**
     * Method for getting type of the meal
     *
     * @return typeOfMealEnum
     */
    public TypeOfMealEnum getTypeOfMealEnum(){ return typeOfMealEnum;}

    /**
     * Constructor
     *
     * @param name
     * @param amount
     * @param kcal
     * @param typeOfMealEnum
     * @param note
     */
    public Meals(String name, String note, int kcal, int amount, TypeOfMealEnum typeOfMealEnum){
        this.name = name;
        this.note = note;
        this.kcal = kcal;
        this.amount = amount;
        this.typeOfMealEnum = typeOfMealEnum;
    }

}
