package app;
/**
 * Class of MealPlan object
 *
 * @author petr.novak
 */
public class MealPlan {

    private String name;
    private Meals breakfast;
    private Meals lunch;
    private Meals dinner;
    private Meals snack;
    private String note;
    private int totalKcal;

    /**
     * Constructor
     *
     * @param name
     * @param breakfast
     * @param lunch
     * @param dinner
     * @param snack
     * @param note
     */
    public MealPlan(String name, Meals breakfast, Meals lunch, Meals dinner, Meals snack, String note){
        this.name = name;
        this.breakfast = breakfast;
        this.lunch = lunch;
        this.dinner = dinner;
        this.snack = snack;
        this.note = note;
        this.totalKcal = breakfast.getKcal() + lunch.getKcal() + dinner.getKcal() + snack.getKcal();
    }

    /**
     * Method for getting name of MealPlan
     *
     * @return name
     */
    public String getName() {
        return name;
    }
    /**
     * Method for getting breakfast of the plan
     *
     * @return breakfast
     */
    public Meals getBreakfast() {
        return breakfast;
    }
    /**
     * Method for getting lunch of the plan
     *
     * @return lunch
     */
    public Meals getLunch() {
        return lunch;
    }
    /**
     * Method for getting dinner of the plan
     *
     * @return dinner
     */
    public Meals getDinner() {
        return dinner;
    }
    /**
     * Method for getting snack of the plan
     *
     * @return snack
     */
    public Meals getSnack() {
        return snack;
    }

    /**
     * Method for getting note of the plan
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * Method for getting total Kcal of the plan
     *
     * @return totalKcal
     */
    public int getTotalKcal() {
        return totalKcal;
    }



}
