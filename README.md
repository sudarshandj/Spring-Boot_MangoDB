# Spring-Boot_MangoDB
Below link is used for spring data examples for pratices

https://github.com/spring-projects/spring-data-examples


#Circuitbreaker implemention for springboot microservices steps as below:

1. First, you need to add the Resilience4j Circuit Breaker dependency to your project. You can do this by adding the following dependency to your pom.xml file:
```
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-circuitbreaker</artifactId>
    <version>1.7.1</version>
</dependency>
```
2. Then, you can configure the Circuit Breaker by creating a YAML file, like this: 
```
resilience4j:
  circuitbreaker:
    configs:
      default:
        registerHealthIndicator: true
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5000
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.dao.TransientDataAccessResourceException
    instances:
      myCircuitBreaker:
        baseConfig: default
        slidingWindowSize: 5
        permittedNumberOfCallsInHalfOpenState: 2
        waitDurationInOpenState: 10000
        failureRateThreshold: 60
```

Description: This sets up a Circuit Breaker with a sliding window size of 10, a minimum number of calls of 5, a failure rate threshold of 50%, and a wait duration in the open state of 5 seconds. It also configures a specific Circuit Breaker instance named myCircuitBreaker with a sliding window size of 5, a permitted number of calls in half-open state of 2, a wait duration in open state of 10 seconds, and a failure rate threshold of 60%. Additionally, it specifies that the Circuit Breaker should handle retryable transient errors of type org.springframework.dao.TransientDataAccessResourceException.

OR
Using Java Configuration:
    Then, you can configure the Circuit Breaker by creating a bean in your Spring configuration class, like this:

```
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50) // Set the failure rate threshold to 50%
                .ringBufferSizeInClosedState(2) // Set the ring buffer size to 2
                .build();

        return CircuitBreaker.of("myCircuitBreaker", config);
    }
}

``

This creates a CircuitBreaker bean with a failure rate threshold of 50% and a ring buffer size of 2.


3. Next, you can use the Circuit Breaker to handle retryable transient errors in your microservice by adding the @CircuitBreaker annotation to your retryable method, like this:

```
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @CircuitBreaker(name = "myCircuitBreaker", fallbackMethod = "fallbackMethod")
    public String myRetryableMethod() {
        // Your retryable code here
    }

    public String fallbackMethod(Exception e) {
        // Your fallback code here
    }
}
```

This adds Circuit Breaker behavior to the myRetryableMethod() method. If the method throws a retryable transient error, the Circuit Breaker will trip and call the fallbackMethod() method instead. The fallbackMethod() method should handle the error and return a default or alternative response.

That's it! With this configuration, your microservice will be more resilient to retryable transient errors, and your users will have a better experience.
