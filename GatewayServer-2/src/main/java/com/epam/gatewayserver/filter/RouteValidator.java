package com.epam.gatewayserver.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> apiEndPoints = List.of(
            "/auth/token",
            "/auth/validate",
            "/eureka",
            "/gym/trainee/registration",
            "/gym/trainer/registration"
    );

    public Predicate<ServerHttpRequest> isSecured =
            serverHttpRequest -> apiEndPoints
                    .stream()
                    .noneMatch(uri -> serverHttpRequest.getURI().getPath().contains(uri));
}
