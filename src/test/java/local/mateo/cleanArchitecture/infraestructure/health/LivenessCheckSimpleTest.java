package local.mateo.cleanArchitecture.infraestructure.health;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponse.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LivenessCheckSimpleTest {

    private LivenessCheckSimple livenessCheckSimple;

    @BeforeEach
    public void setUp() {
        livenessCheckSimple = new LivenessCheckSimple();
    }

    @Test
    public void testCall() {

        HealthCheckResponse result = livenessCheckSimple.call();

        assertEquals(result.getStatus(), Status.UP);
        assertEquals(result.getName(), "Liveness: Simple health check");
    }
}
