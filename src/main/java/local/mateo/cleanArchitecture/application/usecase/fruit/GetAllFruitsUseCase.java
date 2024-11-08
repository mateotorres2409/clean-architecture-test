package local.mateo.cleanArchitecture.application.usecase.fruit;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class GetAllFruitsUseCase {

    private final FruitRepository fruitRepository;

    @Inject
    public GetAllFruitsUseCase(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<FruitModel> execute() {
        return fruitRepository.findAll();
    }
}