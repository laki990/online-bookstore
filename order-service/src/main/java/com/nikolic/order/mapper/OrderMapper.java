package com.nikolic.order.mapper;

import com.nikolic.order.dto.OrderDTO;
import com.nikolic.order.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toOrderDTO(Order order);

    Order toOrder(OrderDTO orderDTO);
}
