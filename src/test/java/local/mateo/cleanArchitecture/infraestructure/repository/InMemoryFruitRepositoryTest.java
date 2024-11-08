package local.mateo.cleanArchitecture.infraestructure.repository;

import local.mateo.cleanArchitecture.domain.model.FruitModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryFruitRepositoryTest {

    private InMemoryFruitRepository fruitRepository;

    @BeforeEach
    public void setUp() {
        fruitRepository = new InMemoryFruitRepository();
    }

    @Test
    public void testSaveFruit() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        fruitRepository.save(fruit);

        Optional<FruitModel> result = fruitRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Manzana", result.get().getName());
    }

    @Test
    public void testFindById_FruitExists() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        fruitRepository.save(fruit);

        Optional<FruitModel> result = fruitRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Manzana", result.get().getName());
    }

    @Test
    public void testFindById_FruitDoesNotExist() {
        Optional<FruitModel> result = fruitRepository.findById(99L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testFindAll() {
        FruitModel fruit1 = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        FruitModel fruit2 = new FruitModel(2L, "Pera", "Fruta verde y jugosa");
        fruitRepository.save(fruit1);
        fruitRepository.save(fruit2);

        List<FruitModel> fruits = fruitRepository.findAll();
        assertEquals(2, fruits.size());
    }

    @Test
    public void testDeleteById_FruitExists() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        fruitRepository.save(fruit);

        fruitRepository.deleteById(1L);
        Optional<FruitModel> result = fruitRepository.findById(1L);
        assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteById_FruitDoesNotExist() {
        // No debería lanzar una excepción, solo probar que no existe
        fruitRepository.deleteById(99L);
        List<FruitModel> fruits = fruitRepository.findAll();
        assertEquals(0, fruits.size());
    }

    @Test
    public void testUpdateFruit_Success() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        fruitRepository.save(fruit);

        FruitModel updatedFruit = new FruitModel(1L, "Manzana Verde", "Fruta verde y crujiente");
        fruitRepository.update(updatedFruit);

        Optional<FruitModel> result = fruitRepository.findById(1L);
        assertTrue(result.isPresent());
        assertEquals("Manzana Verde", result.get().getName());
        assertEquals("Fruta verde y crujiente", result.get().getDescription());
    }

    @Test
    public void testUpdateFruit_Null() {
        FruitModel fruit = new FruitModel(1L, "Manzana", "Fruta roja y dulce");
        fruitRepository.save(fruit);

        FruitModel updatedFruit = new FruitModel(4L, "Manzana Verde", "Fruta verde y crujiente");
        FruitModel result = fruitRepository.update(updatedFruit);

        assertNull(result);
    }
}
