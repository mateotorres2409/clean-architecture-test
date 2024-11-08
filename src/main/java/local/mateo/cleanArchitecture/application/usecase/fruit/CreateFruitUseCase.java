package local.mateo.cleanArchitecture.application.usecase.fruit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class CreateFruitUseCase {
    private final FruitRepository fruitRepository;

    @Inject
    public CreateFruitUseCase(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public FruitModel execute(FruitModel fruit) {
        return fruitRepository.save(fruit);
    }
}
