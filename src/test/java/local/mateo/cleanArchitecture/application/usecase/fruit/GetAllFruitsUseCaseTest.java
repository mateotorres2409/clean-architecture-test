package local.mateo.cleanArchitecture.application.usecase.fruit;

import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GetAllFruitsUseCaseTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private GetAllFruitsUseCase getAllFruitsUseCase;

    private List<FruitModel> fruits,fruitsEmpty;

    @BeforeEach
    void setUp() {
        fruits = Arrays.asList(
                new FruitModel(1L, "Manzana", "Fruta roja"),
                new FruitModel(2L, "Banana", "Fruta amarilla")
        );
        fruitsEmpty = Arrays.asList();
    }

    @Test
    void testGetAllFruits_Success() {
        // Simular la respuesta del repositorio
        when(fruitRepository.findAll()).thenReturn(fruits);

        List<FruitModel> result = getAllFruitsUseCase.execute();

        // Verificar que el repositorio fue llamado
        verify(fruitRepository, times(1)).findAll();

        // Verificar los resultados
        assertEquals(2, result.size());
        assertEquals("Manzana", result.get(0).getName());
    }

    @Test
    void testGetAllFruits_NotFound() {
        // Simular la respuesta del repositorio
        when(fruitRepository.findAll()).thenReturn(fruitsEmpty);

        List<FruitModel> result = getAllFruitsUseCase.execute();

        // Verificar que el repositorio fue llamado
        verify(fruitRepository, times(1)).findAll();

        // Verificar los resultados
        assertEquals(0, result.size());
    }
}
