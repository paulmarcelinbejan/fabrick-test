package io.github.paulmarcelinbejan.fabrick.mapper;

import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import io.github.paulmarcelinbejan.fabrick.dto.accounttransactions.TransactionDto;
import io.github.paulmarcelinbejan.fabrick.entity.Transaction;
import io.github.paulmarcelinbejan.fabrick.mapper.config.FabrickMapperConfig;

@Mapper(config = FabrickMapperConfig.class)
public interface TransactionMapper {

	@Named("toEntity")
	Transaction toEntity(TransactionDto dto);

	@IterableMapping(qualifiedByName = "toEntity")
	List<Transaction> toEntities(List<TransactionDto> dtos);

}
