package local.mateo.cleanArchitecture.application.usecase.fruit;

import local.mateo.cleanArchitecture.domain.repository.FruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DeleteFruitByIdUseCaseTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private DeleteFruitByIdUseCase deleteFruitByIdUseCase;

    private Long fruitId;

    @BeforeEach
    void setUp() {
        fruitId = 1L;
    }

    @Test
    void testDeleteFruitById_Success() {
        when(fruitRepository.deleteById(fruitId)).thenReturn(true);

        boolean result = deleteFruitByIdUseCase.execute(fruitId);
        verify(fruitRepository, times(1)).deleteById(fruitId);
        
        assertTrue(result);
    }

    @Test
    void testDeleteFruitById_NotFound() {
        when(fruitRepository.deleteById(fruitId)).thenReturn(false);

        boolean result = deleteFruitByIdUseCase.execute(fruitId);
        verify(fruitRepository, times(1)).deleteById(fruitId);

        assertFalse(result);
    }
}
