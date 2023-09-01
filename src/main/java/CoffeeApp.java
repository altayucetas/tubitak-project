import altay.yucetas.application.CoffeeMachine;
import java.util.Scanner;


public class CoffeeApp {
    public static void main(String[] args) {

        Scanner getInp = new Scanner(System.in);
        CoffeeMachine myMach = new CoffeeMachine();

        System.out.print("Lütfen içmek istediğiniz kahvenin numarasını giriniz: ");
        int coffeeNum = Integer.parseInt(getInp.nextLine());

        if(coffeeNum > 10000){
            myMach.accessAPI(coffeeNum);
            System.out.print("Lütfen içmek istediğiniz kahvenin numarasını giriniz: ");
            coffeeNum = Integer.parseInt(getInp.nextLine());
        }

        System.out.println(myMach.getCoffeeInfo(coffeeNum));



    }
}