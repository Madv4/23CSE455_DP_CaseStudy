package com.infosys.gateway.Filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthFilter extends AbstractGatewayFilterFactory<JwtAuthFilter.Config> {
    public JwtAuthFilter() { super(Config.class); }
    @Override
    public GatewayFilter apply(Config config) { return (exchange, chain) -> chain.filter(exchange); }
    public static class Config {}
}
