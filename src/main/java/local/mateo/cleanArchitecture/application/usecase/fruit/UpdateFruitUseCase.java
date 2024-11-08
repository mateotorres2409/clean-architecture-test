package local.mateo.cleanArchitecture.application.usecase.fruit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class UpdateFruitUseCase {

    private final FruitRepository fruitRepository;

    @Inject
    public UpdateFruitUseCase(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public FruitModel execute(FruitModel updatedFruit) {
        return fruitRepository.update(updatedFruit);
    }
}