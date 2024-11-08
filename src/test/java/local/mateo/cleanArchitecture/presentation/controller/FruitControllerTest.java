package local.mateo.cleanArchitecture.presentation.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;


@QuarkusTest
public class FruitControllerTest {

    @InjectMock
    private FruitRepository fruitRepository;

    private List<FruitModel> fruits;


    @Test
    public void testGetAllFruits_Success(){
        fruits = Arrays.asList(
                new FruitModel(1L, "Manzana", "Fruta roja"),
                new FruitModel(2L, "Banana", "Fruta amarilla")
        );

        when(fruitRepository.findAll()).thenReturn(fruits);

        given()
            .when().get("/fruit")
            .then()
                .statusCode(200)
                .body("[0].id", equalTo(1))
                .body("[0].name", equalTo("Manzana"))
                .body("[0].description", equalTo("Fruta roja"))
                .body("[1].id", equalTo(2))
                .body("[1].name", equalTo("Banana"))
                .body("[1].description", equalTo("Fruta amarilla"));
    }

    @Test
    public void testGetFruitById_Success() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        
        when(fruitRepository.findById(anyLong())).thenReturn(Optional.of(fruit));
        
        given()
            .pathParam("id", 1L)
            .when().get("/fruit/{id}")
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Manzana"))
                .body("description", equalTo("Fruta roja y dulce"));
    }

    @Test
    public void testCreateFruit_Success() {
        // Creamos un objeto de fruta para enviar en el body
        FruitModel fruit = new FruitModel(null, "Pera", "Fruta amarilla");

        // Simulamos que el caso de uso crea la fruta correctamente
        when(fruitRepository.save(any(FruitModel.class))).thenReturn(new FruitModel(1L, "Pera", "Fruta amarilla"));

        // Realizamos la petición POST para crear la fruta
        given()
            .contentType(ContentType.JSON)
            .body(fruit)
            .when().post("/fruit")
            .then()
                .statusCode(201)
                .body("id", equalTo(1))
                .body("name", equalTo("Pera"))
                .body("description", equalTo("Fruta amarilla"));
    }

    @Test
    public void testDeleteFruitById_Success() {
        Long fruitId = 1L;
        when(fruitRepository.deleteById(anyLong())).thenReturn(true);
        given()
            .when().delete("/fruit/{id}", fruitId)
            .then()
                .statusCode(204);  
    }

    @Test
    public void testDeleteFruitById_NotFound() {
        Long fruitId = 1L;
        when(fruitRepository.deleteById(anyLong())).thenReturn(false);
        given()
            .when().delete("/fruit/{id}", fruitId)
            .then()
                .statusCode(404);  
    }


    @Test
    public void testUpdateFruit_Success() {
        Long fruitId = 1L;
        FruitModel fruit = new FruitModel(fruitId, "Pera", "Fruta amarilla");

        // Simulamos que el caso de uso crea la fruta correctamente
        when(fruitRepository.update(any(FruitModel.class))).thenReturn(new FruitModel(1L, "Pera", "Fruta amarilla"));

        // Realizamos la petición POST para crear la fruta
        given()
            .contentType(ContentType.JSON)
            .body(fruit)
            .when().put("/fruit/{id}", fruitId)
            .then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("name", equalTo("Pera"))
                .body("description", equalTo("Fruta amarilla"));
    }
    @Test
    public void testUpdateFruit_BadRequest() {
        Long fruitId = 1L;
        FruitModel fruit = new FruitModel(fruitId, "Pera", "Fruta amarilla");

        given()
            .contentType(ContentType.JSON)
            .body(fruit)
            .when().put("/fruit/{id}", 2L)
            .then()
                .statusCode(400);
    }

    @Test
    public void testUpdateFruit_NotFound() {
        Long fruitId = 1L;
        FruitModel fruit = new FruitModel(fruitId, "Pera", "Fruta amarilla");

        // Simulamos que el caso de uso crea la fruta correctamente
        when(fruitRepository.update(any(FruitModel.class))).thenReturn(null);

        // Realizamos la petición POST para crear la fruta
        given()
            .contentType(ContentType.JSON)
            .body(fruit)
            .when().put("/fruit/{id}", fruitId)
            .then()
                .statusCode(404);
    }
}
