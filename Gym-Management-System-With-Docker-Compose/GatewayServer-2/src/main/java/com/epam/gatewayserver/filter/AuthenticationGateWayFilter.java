package com.epam.gatewayserver.filter;

import java.util.Objects;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import com.epam.gatewayserver.eception.MissingAuthorizationException;
import com.epam.gatewayserver.proxy.WebFluxAuthenticationProxy;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class AuthenticationGateWayFilter extends AbstractGatewayFilterFactory<AuthenticationGateWayFilter.Config> {

    private static final String GATEWAY_FILTER_API_INVOKED = "method {} invoked in TrainingReportServiceImpl class";
    private static final String GATEWAY_FILTER_API_EXITED = "method {} exited in TrainingReportServiceImpl class";

    private final RouteValidator routeValidator;
//    private final JwtService jwtService;
//    private final AuthenticationProxy authenticationProxy;
    private final WebFluxAuthenticationProxy authenticationProxy;

    public static class Config {

    }

    public AuthenticationGateWayFilter(RouteValidator routeValidator, WebFluxAuthenticationProxy authenticationProxy) {
        super(Config.class);
        this.routeValidator = routeValidator;
        this.authenticationProxy = authenticationProxy;
//        this.jwtService=jwtService;
    }

    @Override
    public GatewayFilter apply(Config config) {
        log.info(GATEWAY_FILTER_API_INVOKED,"apply");
        GatewayFilter gatewayFilter =  ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                log.info("it is not one of the bypass endpoints.");
                if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    log.info("User missing Authorization headers.");
                    throw new MissingAuthorizationException("Missing Authorization Header");
                }

                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if(authHeader != null && authHeader.startsWith("Bearer ")) {
                    log.info("Fetching headers from given request to validate");
                    authHeader = authHeader.substring(7);
                }
//                try {
//                    log.info("try block");
//                    authenticationProxy.validateToken(authHeader);
//
//                }catch (Exception exception) {
//                    log.info("exception class : {}",exception.getClass().getName());
//                    throw new UnAuthorizedException("Un Authorized access to Application");
//                }
                return validateToken(authHeader)
                		.flatMap(valid -> {
                		if (valid) {
                		log.info("Valid token received");
                		return chain.filter(exchange);
                		} else {
                		log.info("Token validation failed");
                		ServerHttpResponse response = exchange.getResponse();
                		response.setStatusCode(HttpStatus.UNAUTHORIZED);
                		return response.setComplete();
                		}
                		});
            }
            return chain.filter(exchange);
        });
        log.info(GATEWAY_FILTER_API_EXITED,"apply");
        return gatewayFilter;
    }
    private Mono<Boolean> validateToken(String token) {
    	log.info("Validating token : " + token);
    	return authenticationProxy.validateToken(token)
    	.map(response -> response.equals("Token is valid")); // Adapt this based on your authentication response
    	}
}
