package com.seguritech.traccar.app.service.mapper;

import com.seguritech.traccar.app.domain.*;
import com.seguritech.traccar.app.service.dto.UltimaUbicacionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UltimaUbicacion and its DTO UltimaUbicacionDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UltimaUbicacionMapper extends EntityMapper<UltimaUbicacionDTO, UltimaUbicacion> {

    

    

    default UltimaUbicacion fromId(Long id) {
        if (id == null) {
            return null;
        }
        UltimaUbicacion ultimaUbicacion = new UltimaUbicacion();
        ultimaUbicacion.setId(id);
        return ultimaUbicacion;
    }
}
