package petstore;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Pesquisar extends PetstoreApi {
	
	// Pesquisa por pets válidos

    @Test
    @Order(1)
    public void pesquisarPetsDisponiveis() {
        given()
        .when()
            .get("/pet/findByStatus?status=available")
        .then()
            .statusCode(200)
            .log().all();
    }
    
    // Pesquisar por um pet inexistente

    @Test
    @Order(2)
    public void pesquisarPetInexistente() {
        int petIdInexistente = 99999999;

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/pet/" + petIdInexistente)
        .then()
            .statusCode(404)
            .body("message", equalTo("Pet not found"));
    }
    
    // Pet Existente

    @Test
    @Order(3)
    public void pesquisarPetExistente() {
        long petId = 112233L;

        given()
            .contentType(ContentType.JSON)
        .when()
            .get("/pet/" + petId)
        .then()
            .statusCode(200)
            .body("category.id", equalTo(30))
            .body("name", equalTo("Postman"))
            .body("tags.name", contains("Doberman"))
            .body("status", equalTo("available"));
    }

}
