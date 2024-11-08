package local.mateo.cleanArchitecture.presentation.controller;

import java.util.List;
import java.util.Optional;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import local.mateo.cleanArchitecture.application.usecase.fruit.CreateFruitUseCase;
import local.mateo.cleanArchitecture.application.usecase.fruit.DeleteFruitByIdUseCase;
import local.mateo.cleanArchitecture.application.usecase.fruit.GetAllFruitsUseCase;
import local.mateo.cleanArchitecture.application.usecase.fruit.GetFruitByIdUseCase;
import local.mateo.cleanArchitecture.application.usecase.fruit.UpdateFruitUseCase;
import local.mateo.cleanArchitecture.domain.model.FruitModel;

@Path("/fruit")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FruitController {

    @Inject
    private CreateFruitUseCase createFruitUseCase;

    @Inject
    private GetAllFruitsUseCase getAllFruitsUseCase;

    @Inject
    private GetFruitByIdUseCase getFruitByIdUseCase;

    @Inject
    private DeleteFruitByIdUseCase deleteFruitByIdUseCase;

    @Inject
    private UpdateFruitUseCase updateFruitUseCase;

    @POST
    public Response createFruit (FruitModel fruit){
        FruitModel createdFruit = createFruitUseCase.execute(fruit);
        return Response.status(Response.Status.CREATED).entity(createdFruit).build();
    }

    @GET
    public Response getAllFruits() {
        List<FruitModel> fruits = getAllFruitsUseCase.execute();
        return Response.ok(fruits).build();
    }

    @GET
    @Path("/{id}")
    public Response getFruitByID(@PathParam("id") Long id) {
        Optional<FruitModel> fruit = getFruitByIdUseCase.execute(id);
        return fruit.map(value -> Response.ok(value).build())
                    .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @DELETE
    @Path("/{id}")
    public Response deleteFruitById(@PathParam("id") Long id) {
        boolean deleted = deleteFruitByIdUseCase.execute(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build(); // No content, successful deletion
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // Fruit not found
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateFruit(@PathParam("id") Long id, FruitModel fruit) {
        if (!id.equals(fruit.getId())) {
            return Response.status(Response.Status.BAD_REQUEST).build(); // El id en la URL debe coincidir con el id de la fruta
        }
        FruitModel updatedFruit = updateFruitUseCase.execute(fruit);
        if (updatedFruit != null) {
            return Response.ok(updatedFruit).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); 
        }
    }

}
