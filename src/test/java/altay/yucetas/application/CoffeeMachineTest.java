package altay.yucetas.application;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

// CoffeeMachine sınıfının unit testleri yapılmaktadır.
class CoffeeMachineTest {
    CoffeeMachine myMach;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream stdin = System.in;

    @BeforeEach
    void init(){
        myMach = new CoffeeMachine();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void endit(){
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    @DisplayName("API erişimi için geliştirici ID'si kontrol edilmektedir")
    void accessAPIDevID(){
        String exitOption = "3\n";
        String expected = "Geliştirici menüsüne hoş geldiniz.1. Kahve Ekleme2. Kahve Çıkarma3. ÇıkışLütfen yapmak istediğiniz işlemi seçiniz: Çıkış yapılıyor.";

        InputStream in = new ByteArrayInputStream(exitOption.getBytes());
        System.setIn(in);

        assertDoesNotThrow(() -> myMach.accessAPI(612546903));

        assertEquals(expected, out.toString().replaceAll("\n", "").replaceAll("\r", ""), "ID kontrolü yapılamadı");
    }

    @Test
    @DisplayName("API fonksiyonlarından 'add'in çalışıp çalışmadığı kontrol edilmektedir")
    void accessAPIAddCoffee(){
        String input = "1\n1\n";
        String firstCoffee = "Turkish Tea\n25\n3xHot Water, 1xTea Leave\n";
        input += firstCoffee;

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(() -> myMach.accessAPI(612546903));

        String expectedMenu = """
        1. Espresso (20 TL)
        2. Double Espresso (29 TL)
        3. Cappuccino (27 TL)
        4. Caffe Latte (27 TL)
        5. Mocha (32 TL)
        6. Americano (25 TL)
        7. Hot Water (5 TL)
        8. Turkish Tea (25 TL)
        """;

        System.setIn(stdin);
        assertEquals(expectedMenu, myMach.printMenu(), "Doğru kahve eklenemedi");
    }

    @Test
    @DisplayName("API fonksiyonlarından 'delete'in çalışıp çalışmadığı kontrol edilmektedir")
    void accessAPIDeleteCoffee(){
        String input = "2\n1\n";
        String firstCoffee = "Caffe Latte\n";
        input += firstCoffee;

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        assertDoesNotThrow(() -> myMach.accessAPI(612546903));

        String expectedMenu = """
        1. Espresso (20 TL)
        2. Double Espresso (29 TL)
        3. Cappuccino (27 TL)
        4. Mocha (32 TL)
        5. Americano (25 TL)
        6. Hot Water (5 TL)
        """;

        System.setIn(stdin);
        assertEquals(expectedMenu, myMach.printMenu(), "Doğru kahve silinemedi");
    }

    @Test
    @DisplayName("Doğru kahvenin alınıp alınmadığı kontrol edilmektedir")
    void getCoffeeInfo() {
        String succTest = "Double Espresso seçtiniz. Bu içeceğimiz 2 doz Espresso içermektedir. Afiyet olsun.";
        assertEquals(succTest, myMach.getCoffeeInfo(2), "Doğru kahve bilgileri alınamadı");

        String failTest = "Lütfen geçerli bir kahve numarası giriniz!";
        assertEquals(failTest, myMach.getCoffeeInfo(15), "İmkansız");
    }
}