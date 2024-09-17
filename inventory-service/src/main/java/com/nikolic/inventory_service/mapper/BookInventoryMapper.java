package com.nikolic.inventory_service.mapper;

import com.nikolic.inventory_service.dto.BookInventoryDto;
import com.nikolic.inventory_service.model.BookInventory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookInventoryMapper {

    BookInventoryMapper INSTANCE = Mappers.getMapper(BookInventoryMapper.class);

    BookInventoryDto inventoryBookToInventoryBookDto (BookInventory bookInventory);

    BookInventory bookInventoryDtoToBookInventory(BookInventoryDto bookInventoryDto);
}
