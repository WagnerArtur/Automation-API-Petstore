package petstore;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;


public class PetstoreApi {
	
	@BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

}
