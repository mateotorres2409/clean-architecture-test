package local.mateo.cleanArchitecture.infraestructure.health;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponse.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReadinessCheckDBTest {
   
    private ReadinessCheckDB readinessCheckDB;

    @BeforeEach
    public void setUp() {
        readinessCheckDB = new ReadinessCheckDB();
    }

    @Test
    public void testCallDown() {
        readinessCheckDB.setDatabaseUp(false);
        HealthCheckResponse result = readinessCheckDB.call();
        assertEquals(result.getStatus(), Status.DOWN);
        assertEquals(result.getName(), "Readiness: Database connection health check");
        assertTrue(result.getData().get().containsKey("error"));
    }

    @Test
    public void testCallUp() {
        readinessCheckDB.setDatabaseUp(true);
        HealthCheckResponse result = readinessCheckDB.call();
        assertEquals(result.getStatus(), Status.UP);
        assertEquals(result.getName(), "Readiness: Database connection health check");
        assertFalse(result.getData().isPresent());
    }
}
