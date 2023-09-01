package altay.yucetas.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

// CoffeeMenu sınıfının unit testleri yapılmaktadır.
class CoffeeMenuTest {

    CoffeeMenu myMenu;
    ArrayList<Coffee> menuList;

    @BeforeEach
    void init(){
        myMenu = new CoffeeMenu();
        menuList = new ArrayList<>();

        menuList.add(new Coffee("Turkish Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee"));
        menuList.add(new Coffee("Greek Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee"));
        menuList.add(new Coffee("Filter Coffee", 25, "4xHot Water, 1xCoffee"));

        myMenu.clearMenu();
        myMenu.addCoffee(new Coffee("Turkish Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee"));
        myMenu.addCoffee(new Coffee("Greek Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee"));
        myMenu.addCoffee(new Coffee("Filter Coffee", 25, "4xHot Water, 1xCoffee"));
    }

    @Test
    @DisplayName("Doğru menünün alınıp alınmadığı kontrol edilmektedir")
    void getMenu() {
        assertEquals(menuList.toString(), myMenu.getMenu().toString(), "Doğru menü alınamadı");
    }

    @Test
    @DisplayName("Doğru kahvenin alınıp alınmadığı kontrol edilmektedir")
    void getCoffee() {
        Coffee testCoffee1 = new Coffee("Turkish Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee");
        Coffee testCoffee2 = new Coffee("Greek Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee");
        Coffee testCoffee3 = new Coffee("Filter Coffee", 25, "4xHot Water, 1xCoffee");

        assertEquals(testCoffee1.toString(), myMenu.getCoffee(0).toString(), "Doğru kahve alınamadı");
        assertEquals(testCoffee2.toString(), myMenu.getCoffee(1).toString(), "Doğru kahve alınamadı");
        assertEquals(testCoffee3.toString(), myMenu.getCoffee(2).toString(), "Doğru kahve alınamadı");
    }

    @Test
    @DisplayName("Doğru menü boyutunun alınıp alınmadığı kontrol edilmektedir")
    void getMenuSize() {
        assertEquals(3, myMenu.getMenuSize(), "Doğru menü boyutu alınamadı");

        myMenu.addCoffee(new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave"));
        assertEquals(4, myMenu.getMenuSize(), "Doğru menü boyutu alınamadı");

        myMenu.clearMenu();
        assertEquals(0, myMenu.getMenuSize(), "Doğru menü boyutu alınamadı");
    }

    @Test
    @DisplayName("Kahvenin eklenip eklenmediği kontrol edilmektedir")
    void addCoffee(){
        menuList.add(new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave"));
        myMenu.addCoffee(new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave"));
        assertEquals(menuList.toString(), myMenu.getMenu().toString(), "Could not add the coffee");

        menuList.add(new Coffee("Cold Water", 5, "5xCold Water"));
        myMenu.addCoffee("Cold Water", 5, "5xCold Water");
        assertEquals(menuList.toString(), myMenu.getMenu().toString(), "Kahve eklenemedi");
    }

    @Test
    @DisplayName("Kahvenin silinip silinmediği kontrol edilmektedir")
    void deleteCoffee(){
        Coffee firstAddedCoffee = new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave");

        menuList.add(firstAddedCoffee);
        myMenu.addCoffee(firstAddedCoffee);

        menuList.remove(1);
        myMenu.deleteCoffee("Greek Coffee");
        assertEquals(menuList.toString(), myMenu.getMenu().toString(), "Kahve silinemedi");

        menuList.remove(2);
        myMenu.deleteCoffee(firstAddedCoffee);
        assertEquals(menuList.toString(), myMenu.getMenu().toString(), "Kahve silinemedi");

        myMenu.deleteCoffee("Not a Coffee");
        assertEquals(menuList.size(), myMenu.getMenuSize(), "İmkansız");
    }

    @Test
    @DisplayName("Menünün temizlenip temizlenmediği kontrol edilmektedir")
    void clearMenu(){
        myMenu.clearMenu();
        assertEquals(new ArrayList<>(), myMenu.getMenu(), "Menü temizlenemedi");

        ArrayList<Coffee> miniList = new ArrayList<>();
        miniList.add(new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave"));

        myMenu.addCoffee(new Coffee("Turkish Tea", 15, "3xHot Water, 1xTea Leave"));
        assertEquals(miniList.toString(), myMenu.getMenu().toString());
    }

    @Test
    @DisplayName("Doğru obje metninin alınıp alınmadığı kontrol edilmektedir")
    void testToString() {
        String test = """
                1. Turkish Coffee (30 TL)
                2. Greek Coffee (30 TL)
                3. Filter Coffee (25 TL)
                """;
        assertEquals(test, myMenu.toString(), "Doğru obje metni alınamadı");
    }
}