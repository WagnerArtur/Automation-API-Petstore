package petstore;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CadastrarPedido extends PetstoreApi {
	
	// Cadastrar novo pedido de pet com sucesso 

    @Test
    @Order(1)
    public void cadastrarPedidoComSucesso() {
        String bodyJson = """
            {
                "id": 11,
                "petId": 2,
                "quantity": 33,
                "shipDate": "2025-08-01T03:58:11.612Z",
                "status": "placed",
                "complete": true
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(bodyJson)
        .when()
            .post("/store/order")
        .then()
            .statusCode(200)
            .body("quantity", equalTo(33))
            .body("petId", equalTo(2));
    }
    
    // Pedido Inválido = petId com string

    @Test
    @Order(2)
    public void cadastrarPedidoInvalido() {
        String bodyJsonInvalido = """
            {
                "id": 12,
                "petId": "valorErrado",
                "shipDate": "2025-08-01T03:58:11.612Z",
                "status": "placed",
                "complete": true
            }
        """;

        given()
            .contentType(ContentType.JSON)
            .body(bodyJsonInvalido)
        .when()
            .post("/store/order")
        .then()
            .statusCode(500); // Ou 400 dependendo da API
    }
}
