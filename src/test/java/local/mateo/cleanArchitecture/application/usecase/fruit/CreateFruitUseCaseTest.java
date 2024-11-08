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
public class CreateFruitUseCaseTest {

    @Mock
    private FruitRepository fruitRepository;

    @InjectMocks
    private CreateFruitUseCase createFruitUseCase;

    private FruitModel fruit;

    @BeforeEach
    void setUp() {
        fruit = new FruitModel();
        fruit.setName("Manzana");
        fruit.setDescription("Fruta roja y crujiente");
    }

    @Test
    void testCreateFruit() {
        // Simular la creación de la fruta
        when(fruitRepository.save(fruit)).thenReturn(fruit);

        FruitModel createdFruit = createFruitUseCase.execute(fruit);

        // Verificar que el repositorio fue llamado
        verify(fruitRepository, times(1)).save(fruit);

        // Verificar que la fruta fue creada correctamente
        assertNotNull(createdFruit);
        assertEquals("Manzana", createdFruit.getName());
        assertEquals("Fruta roja y crujiente", createdFruit.getDescription());
    }
}
