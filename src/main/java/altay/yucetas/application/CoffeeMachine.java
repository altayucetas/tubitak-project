package altay.yucetas.application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CoffeeMachine {
    private final CoffeeMenu myMenu = new CoffeeMenu();
    public CoffeeMachine(){
        System.out.println("Merhaba, kahve makinesine hoş geldiniz. Menü ve fiyatlar aşağıda görüldüğü gibidir.");
        System.out.println(myMenu);
    }

    public String printMenu(){
        return myMenu.toString();
    }

    public void accessAPI(int devID){
        if(devID == 612546903){
            Scanner getDevInp = new Scanner(System.in);
            int devProcess;
            String opening = """
                    Geliştirici menüsüne hoş geldiniz.
                    1. Kahve Ekleme
                    2. Kahve Çıkarma
                    3. Çıkış
                    Lütfen yapmak istediğiniz işlemi seçiniz:\s""";

            System.out.print(opening);

            devProcess = Integer.parseInt(getDevInp.nextLine());

            if(devProcess == 1){
                int addedNum;
                String coffeeName;
                int coffeePrice;
                String coffeeRecipe;

                System.out.print("Kaç adet kahve eklemek istiyorsunuz: ");
                addedNum = Integer.parseInt(getDevInp.nextLine());

                for(int i=0; i<addedNum; i++){
                    System.out.print("Lütfen " + (i+1) + ". kahvenin adını giriniz: ");
                    coffeeName = getDevInp.nextLine();

                    System.out.print("Lütfen " + (i+1) + ". kahvenin fiyatını giriniz: ");
                    coffeePrice = Integer.parseInt(getDevInp.nextLine());

                    System.out.print("Lütfen " + (i+1) + ". kahvenin tarifini giriniz: ");
                    coffeeRecipe = getDevInp.nextLine();

                    myMenu.addCoffee(coffeeName, coffeePrice, coffeeRecipe);
                }

                System.out.println("Kahveler başarı ile eklenmiştir.");
                System.out.println(this.printMenu());
            }
            else if (devProcess == 2) {
                int deletedNum;
                String coffeeName;

                System.out.print("Kaç adet kahve silmek istiyorsunuz: ");
                deletedNum = Integer.parseInt(getDevInp.nextLine());

                for(int i=0; i<deletedNum; i++){
                    System.out.print("Lütfen " + (i+1) + ". kahvenin adını giriniz: ");
                    coffeeName = getDevInp.nextLine();

                    myMenu.deleteCoffee(coffeeName);
                }

                System.out.println("Kahveler başarı ile silinmiştir.");
                System.out.println(this.printMenu());

            }
            else{
                System.out.println("Çıkış yapılıyor.");
            }
        }
        else{
            System.out.println("Lütfen geçerli bir kullanıcı kimlik numarası giriniz!");
        }
    }
    public String getCoffeeInfo(int selectedNum){
        StringBuilder machineAnswer = new StringBuilder();

        if(selectedNum < 1 || selectedNum > myMenu.getMenuSize()){
            machineAnswer.append("Lütfen geçerli bir kahve numarası giriniz!");
        }
        else{
            Coffee myCoffee = myMenu.getCoffee(selectedNum-1);
            HashMap<String, Integer> myMix = myCoffee.getRecipe();
            int mixNum = 0;

            System.out.println("Teşekkürler, kahveniz hazırlanıyor.");
            try{
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            machineAnswer.append(myCoffee.getName()).append(" seçtiniz. Bu içeceğimiz ");

            for (String ingre: myMix.keySet()) {
                mixNum++;
                machineAnswer.append(myMix.get(ingre)).append(" doz ").append(ingre);

                if(mixNum == myMix.size()-1){
                    machineAnswer.append(" ve ");
                }
                else if (mixNum != myMix.size()){
                    machineAnswer.append(", ");
                }
            }
            machineAnswer.append(" içermektedir. Afiyet olsun.");
        }
        return machineAnswer.toString();
    }
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
