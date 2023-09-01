package altay.yucetas.application;

import java.util.HashMap;

public class CoffeeMachine {
}
class Coffee{
    private final String name;
    private final int price;
    private final HashMap<String, Integer> recipe = new HashMap<>();

    public Coffee(String inpName, int inpPrice, String inpRecipe){
        this.name = inpName;
        this.price = inpPrice;

        String[] splitted = inpRecipe.split(", ", 0);

        for (String subs : splitted) {
            this.recipe.put(subs.substring(2), Integer.parseInt(subs.substring(0, 1)));
        }

    }

    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public HashMap<String, Integer> getRecipe(){
        return recipe;
    }

    @Override
    public String toString() {
        return this.name + " (" + this.price + " TL)";
    }
}
