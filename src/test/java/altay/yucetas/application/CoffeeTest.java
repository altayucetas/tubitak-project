package altay.yucetas.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

// Coffee sınıfının unit testleri yapılmaktadır.
class CoffeeTest {

    Coffee myCoffee;
    HashMap<String, Integer> myRecipe = new HashMap<>();

    @BeforeEach
    void init(){
        myCoffee = new Coffee("Turkish Coffee", 30, "1xHot Water, 2xSugar, 1xCoffee");
    }

    @Test
    @DisplayName("Doğru ismin alınıp alınmadığı kontrol edilmektedir")
    void getName() {
        assertEquals("Turkish Coffee", myCoffee.getName(), "Doğru isim alınamadı");
    }

    @Test
    @DisplayName("Doğru fiyatın alınıp alınmadığı kontrol edilmektedir")
    void getPrice() {
        assertEquals(30, myCoffee.getPrice(), "Doğru fiyat alınamadı");
    }

    @Test
    @DisplayName("Doğru tarifin alınıp alınmadığı kontrol edilmektedir")
    void getRecipe() {
        myRecipe.put("Hot Water", 1);
        myRecipe.put("Sugar", 2);
        myRecipe.put("Coffee", 1);
        assertEquals(myRecipe, myCoffee.getRecipe(), "Doğru tarif alınamadı");
    }

    @Test
    @DisplayName("Doğru obje metninin alınıp alınmadığı kontrol edilmektedir")
    void testToString() {
        assertEquals("Turkish Coffee (30 TL)", myCoffee.toString(), "Doğru obje metni alınamadı");
    }
}