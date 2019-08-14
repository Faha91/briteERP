package briteERP.tests.component;

import com.github.javafaker.Faker;
import org.testng.annotations.Test;

public class ContractTest {
    static public Faker faker = new Faker();
    @Test
    public void Test1(){
        System.out.println("driver open vytrcak" +faker.chuckNorris().fact());

    }
}
