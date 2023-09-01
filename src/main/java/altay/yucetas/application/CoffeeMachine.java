package altay.yucetas.application;

import java.util.ArrayList;
import java.util.HashMap;

public class CoffeeMachine {
}

class CoffeeMenu{
    private final ArrayList<Coffee> menu = new ArrayList<>();
    private int menuSize;

    public CoffeeMenu(){
        menu.add(new Coffee("Espresso", 20, "1xEspresso"));
        menu.add(new Coffee("Double Espresso", 29, "2xEspresso"));
        menu.add(new Coffee("Cappuccino", 27, "1xEspresso, 2xSteamed Milk, 2xMilk Foam"));
        menu.add(new Coffee("Caffe Latte", 27, "1xEspresso, 3xSteamed Milk, 1xMilk Foam"));
        menu.add(new Coffee("Mocha", 32, "1xEspresso, 1xSteamed Milk, 1xMilk Foam, 2xHot Chocolate"));
        menu.add(new Coffee("Americano", 25, "1xEspresso, 4xHot Water"));
        menu.add(new Coffee("Hot Water", 5, "5xHot Water"));
        menuSize = 7;
    }

    public ArrayList<Coffee> getMenu(){
        return menu;
    }

    public Coffee getCoffee(int coffeeNum){
        return menu.get(coffeeNum);
    }
    public int getMenuSize(){
        return menuSize;
    }

    public void addCoffee(String addedName, int addedPrice, String addedRecipe){
        menu.add(new Coffee(addedName, addedPrice, addedRecipe));
        menuSize++;
    }

    public void addCoffee(Coffee addedCoffee){
        menu.add(addedCoffee);
        menuSize++;
    }

    public void deleteCoffee(String removedName){
        for (Coffee selected: menu) {
            if(selected.getName().equals(removedName)){
                menu.remove(selected);
                menuSize--;
                break;
            }
            else if(menu.get(menuSize-1) == selected && !(selected.getName().equals(removedName))){
                System.out.println("Kahve ismi listede bulunamadı.");
            }
        }
    }

    public void deleteCoffee(Coffee removedCoffee){
        for (Coffee selected: menu) {
            if(removedCoffee.getName().equals(selected.getName())){
                menu.remove(selected);
                menuSize--;
                break;
            }
            else if(menu.get(menuSize-1) == selected && !(removedCoffee.getName().equals(selected.getName()))){
                System.out.println("Kahve ismi listede bulunamadı.");
            }
        }
    }
    public void clearMenu(){
        menu.clear();
        menuSize = 0;
    }

    @Override
    public String toString() {
        StringBuilder allMenu = new StringBuilder();
        for (int i=0; i<menu.size(); i++) {
            allMenu.append(i + 1).append(". ").append(menu.get(i)).append("\n");
        }
        return allMenu.toString();
    }
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
