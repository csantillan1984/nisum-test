package com.nisum.user.app.infrastructure.input.adapter.rest.mapper;

import com.nisum.user.app.domain.Phone;
import com.nisum.user.app.infrastructure.output.repository.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    @Mapping(target = "user", ignore = true)
    Phone mapPhoneEntityToPhoneDomain(PhoneEntity phoneEntity);
}
