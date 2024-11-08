package local.mateo.cleanArchitecture.infraestructure.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import jakarta.enterprise.context.ApplicationScoped;

@Readiness
@ApplicationScoped
public class ReadinessCheckDB implements HealthCheck{
 private boolean databaseUp;

    public ReadinessCheckDB() {
        this.databaseUp = false; // Valor por defecto, puede ser modificado.
    }

    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Readiness: Database connection health check");
        try {
            simulateDatabaseConnectionVerification(databaseUp);
            responseBuilder.up();
        } catch (IllegalStateException e) {
            responseBuilder.down().withData("error", e.getMessage());
        }
        return responseBuilder.build();
    }

    // Método para simular la verificación de la base de datos
    public void simulateDatabaseConnectionVerification(boolean databaseUp) {
        if (!databaseUp) {
            throw new IllegalStateException("Cannot contact database");
        }
    }

    // Método para cambiar el estado de la base de datos (usado solo para pruebas)
    public void setDatabaseUp(boolean databaseUp) {
        this.databaseUp = databaseUp;
    }

}
