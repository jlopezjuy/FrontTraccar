package com.seguritech.traccar.app.service.mapper;

import com.seguritech.traccar.app.domain.*;
import com.seguritech.traccar.app.service.dto.DispositivoGpsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DispositivoGps and its DTO DispositivoGpsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DispositivoGpsMapper extends EntityMapper<DispositivoGpsDTO, DispositivoGps> {

    

    

    default DispositivoGps fromId(Long id) {
        if (id == null) {
            return null;
        }
        DispositivoGps dispositivoGps = new DispositivoGps();
        dispositivoGps.setId(id);
        return dispositivoGps;
    }
}
