package com.navent.api.orders.config;

import com.navent.api.orders.contract.OrderDto;
import com.navent.api.orders.domain.Order;
import com.navent.api.orders.persistence.entity.OrderPersistence;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    @Bean
    public MapperFacade mapperFacade() {
        MapperFactory mapperFactory = new Builder().build();

        mapperFactory.classMap(Order.class, OrderPersistence.class).byDefault().register();
        mapperFactory.classMap(OrderDto.class, Order.class).byDefault().register();

        return mapperFactory.getMapperFacade();
    }
}

