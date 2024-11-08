package local.mateo.cleanArchitecture.application.usecase.fruit;

import local.mateo.cleanArchitecture.domain.model.FruitModel;
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
public class UpdateFruitUseCaseTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private UpdateFruitUseCase updateFruitUseCase;

    private FruitModel updatedFruit;

    @BeforeEach
    void setUp() {
        updatedFruit = new FruitModel(1L, "Manzana", "Fruta roja y crujiente");
    }

    @Test
    void testUpdateFruit_Success() {
        // Simular la actualización de la fruta
        when(fruitRepository.update(updatedFruit)).thenReturn(updatedFruit);

        FruitModel result = updateFruitUseCase.execute(updatedFruit);

        // Verificar que el repositorio fue llamado
        verify(fruitRepository, times(1)).update(updatedFruit);

        // Verificar que los datos fueron actualizados correctamente
        assertNotNull(result);
        assertEquals("Manzana", result.getName());
        assertEquals("Fruta roja y crujiente", result.getDescription());
    }

    @Test
    void testUpdateFruit_NotFound() {
        // Simular la actualización de la fruta
        when(fruitRepository.update(updatedFruit)).thenReturn(null);

        FruitModel result = updateFruitUseCase.execute(updatedFruit);

        // Verificar que el repositorio fue llamado
        verify(fruitRepository, times(1)).update(updatedFruit);

        // Verificar que los datos fueron actualizados correctamente
        assertNull(result);
    }
}
