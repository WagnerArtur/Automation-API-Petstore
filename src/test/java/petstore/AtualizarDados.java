package petstore;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AtualizarDados extends PetstoreApi {
	
	// Atualizar dados de um pet existente
	
    @Test
    @Order(1)
    public void AtualizarDadosPet() {

        String bodyJson = """
                {
                    "id": 11,
                    "category": {
                        "id": 1111111111111111111,
                        "name": "Inseto"
                    },
                    "name": "Flik do Bugs Life",
                    "photoUrls": [
                        "https://example.com/FOTOdeTESTE.jpg"
                    ],
                    "tags": [
                        {
                            "id": 333,
                            "name": "Abacate"
                        }
                    ],
                    "status": "available"
                }
                """;	

        given()
            .contentType(ContentType.JSON)
            .body(bodyJson)
          //  .log().all()
        .when()
            .put("/pet")
        .then()
          //  .log().all()
            .statusCode(200)
            .body("id", equalTo(11))
            .body("name", equalTo("Flik do Bugs Life"))
            .body("status", equalTo("available"))
            .body("tags.name", contains("Abacate"))
            .body("category.name", equalTo("Inseto"));
    }
    
    // Atualizar pet inexistente
    
    @Test
    @Order(2)
    public void AtualizarDadosPetInexistente() {

        String bodyJson = """
                {
                    "id": 0000000000000000000000000,
                    "category": {
                        "id": 000000000000000000,
                        "name": "semNome"
                    },
                    "name": "Flik do Bugs Life",
                    "photoUrls": [
                        "https://example.com/SEMFOTO.jpg"
                    ],
                    "tags": [
                        {
                            "id": 000,
                            "name": "Barão"
                        }
                    ],
                    "status": "available"
                }
                """;	

        given()
            .contentType(ContentType.JSON)
            .body(bodyJson)
          //  .log().all()
        .when()
            .put("/pet")
        .then()
          //  .log().all()
            .statusCode(400);
    }
}
