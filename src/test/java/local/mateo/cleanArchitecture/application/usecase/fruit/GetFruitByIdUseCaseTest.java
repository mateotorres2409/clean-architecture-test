package local.mateo.cleanArchitecture.application.usecase.fruit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ExtendWith(MockitoExtension.class)
public class GetFruitByIdUseCaseTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private GetFruitByIdUseCase getFruitByIdUseCase;

    private FruitModel fruit;

    @BeforeEach
    void setUp() {
        fruit = new FruitModel(1L, "Manzana", "Fruta roja");
    }

    @Test
    void testGetFruitById_Success() {       
        Long id = 1L;
        //Definición de respuesta
        when(fruitRepository.findById(id)).thenReturn(Optional.of(fruit));

        Optional<FruitModel> result = getFruitByIdUseCase.execute(id);
        verify(fruitRepository, times(1)).findById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals("Manzana", result.get().getName());
        assertEquals("Fruta roja", result.get().getDescription());
    }

    @Test
    void testGetFruitById_NotFound() {      
        Long id = 1L;
        //Definición de respuesta Mock
        when(fruitRepository.findById(id)).thenReturn(Optional.empty());

        Optional<FruitModel> result = getFruitByIdUseCase.execute(id);
        verify(fruitRepository, times(1)).findById(id);

        assertFalse(result.isPresent());
    }
}
