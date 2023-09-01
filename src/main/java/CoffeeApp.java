import altay.yucetas.application.CoffeeMachine;
import java.util.Scanner;


public class CoffeeApp {
    public static void main(String[] args) {

        Scanner getInp = new Scanner(System.in);
        CoffeeMachine myMach = new CoffeeMachine();

        // Kullanıcıdan kahve numarası değeri alınır.
        System.out.print("Lütfen içmek istediğiniz kahvenin numarasını giriniz: ");
        int coffeeNum = Integer.parseInt(getInp.nextLine());

        // Alınan değer 10000'den fazla ise accessAPI fonksiyonu çalıştırılır.
        // accessAPI fonksiyonu çalıştıktan sonra tekrar kullanıcıdan kahve numarası istenir.
        if(coffeeNum > 10000){
            myMach.accessAPI(coffeeNum);
            System.out.print("Lütfen içmek istediğiniz kahvenin numarasını giriniz: ");
            coffeeNum = Integer.parseInt(getInp.nextLine());
        }

        // Alınan değer 10000'den küçük ise istenilen kahve teslim edilir.
        System.out.println(myMach.getCoffeeInfo(coffeeNum));

    }
}