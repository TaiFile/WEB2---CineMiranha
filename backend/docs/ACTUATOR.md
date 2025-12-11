# Spring Boot Actuator Configuration

## Overview
Spring Boot Actuator provides production-ready features to help you monitor and manage your application. The cinema-api has been configured with comprehensive actuator endpoints.

## Available Endpoints

### Development Environment (`dev` profile)

All actuator endpoints are exposed at: `http://localhost:8080/actuator`

**Available Endpoints:**
- `/actuator/health` - Application health status (detailed)
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics
- `/actuator/env` - Environment properties
- `/actuator/beans` - Spring beans list
- `/actuator/mappings` - Request mappings
- `/actuator/loggers` - Logger configuration
- `/actuator/threaddump` - Thread dump
- `/actuator/heapdump` - Heap dump
- `/actuator/conditions` - Auto-configuration report
- `/actuator/configprops` - Configuration properties
- And all other actuator endpoints (`*` is enabled)

### Production Environment (`prod` profile)

Limited endpoints exposed for security:
- `/actuator/health` - Basic health check (requires authorization for details)
- `/actuator/info` - Application information
- `/actuator/metrics` - Application metrics

## Health Checks

The health endpoint provides:
- **Database connectivity** - Checks PostgreSQL connection
- **Disk space** - Monitors available disk space
- **Liveness probe** - Kubernetes liveness probe support
- **Readiness probe** - Kubernetes readiness probe support

### Health Endpoint Response (Dev)

```json
{
  "status": "UP",
  "components": {
    "db": {
      "status": "UP",
      "details": {
        "database": "PostgreSQL",
        "validationQuery": "isValid()"
      }
    },
    "diskSpace": {
      "status": "UP",
      "details": {
        "total": 123456789,
        "free": 98765432,
        "threshold": 10485760,
        "exists": true
      }
    },
    "livenessState": {
      "status": "UP"
    },
    "readinessState": {
      "status": "UP"
    }
  }
}
```

## Info Endpoint

Provides application metadata:

```json
{
  "app": {
    "name": "cinema-api",
    "description": "API para plataforma de cinemas",
    "version": "0.0.1-SNAPSHOT",
    "encoding": "UTF-8",
    "java": {
      "version": "21"
    }
  }
}
```

## Metrics Endpoint

Available metrics include:
- **JVM metrics** - Memory, threads, garbage collection
- **HTTP metrics** - Request counts, response times, status codes
- **Database metrics** - Connection pool statistics
- **Application metrics** - Custom business metrics

### Example Metrics URLs:
- `/actuator/metrics/jvm.memory.used`
- `/actuator/metrics/http.server.requests`
- `/actuator/metrics/hikaricp.connections.active`

## Security Considerations

### Development
- All endpoints are accessible without authentication
- Detailed health information is always shown
- Suitable for local development and testing

### Production
- Limited endpoints exposed
- Health details require authorization
- Shutdown endpoint is disabled
- Use Spring Security to protect actuator endpoints

## Testing Actuator Endpoints

### Using cURL:

```bash
# Health check
curl http://localhost:8080/actuator/health

# Application info
curl http://localhost:8080/actuator/info

# List all available endpoints
curl http://localhost:8080/actuator

# Get specific metric
curl http://localhost:8080/actuator/metrics/jvm.memory.used
```

### Using Browser:
Simply navigate to `http://localhost:8080/actuator` to see all available endpoints.

## Configuration Files

- `application.yml` - Base actuator configuration
- `application-dev.yml` - Development-specific configuration (all endpoints)
- `application-prod.yml` - Production-specific configuration (limited endpoints)

## Switching Profiles

### Development (default):
```bash
./mvnw spring-boot:run
```

### Production:
```bash
./mvnw spring-boot:run -Dspring-boot.run.arguments=--spring.profiles.active=prod
```

Or set environment variable:
```bash
export SPRING_PROFILES_ACTIVE=prod
java -jar cinema-api-0.0.1-SNAPSHOT.jar
```

## Integration with Monitoring Tools

### Prometheus
Actuator can be integrated with Prometheus for metrics collection. Add the following dependency if needed:

```xml
<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

### Grafana
Use the metrics endpoint data to create Grafana dashboards.

### Kubernetes Health Probes

```yaml
livenessProbe:
  httpGet:
    path: /actuator/health/liveness
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 10

readinessProbe:
  httpGet:
    path: /actuator/health/readiness
    port: 8080
  initialDelaySeconds: 10
  periodSeconds: 5
```

## Custom Metrics

You can add custom metrics to your application:

```java
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetrics {
    private final MeterRegistry meterRegistry;

    public CustomMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        meterRegistry.counter("cinema.tickets.sold").increment();
    }
}
```

## Troubleshooting

### Endpoints not accessible
1. Check if actuator dependency is in pom.xml
2. Verify Spring Security configuration permits actuator endpoints
3. Check active profile configuration

### Health check shows DOWN
1. Check database connectivity
2. Verify disk space
3. Check application logs for errors

## References
- [Spring Boot Actuator Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html)
- [Micrometer Documentation](https://micrometer.io/docs)

