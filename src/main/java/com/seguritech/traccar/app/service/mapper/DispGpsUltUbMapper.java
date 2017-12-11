package com.seguritech.traccar.app.service.mapper;

import com.seguritech.traccar.app.domain.*;
import com.seguritech.traccar.app.service.dto.DispGpsUltUbDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity DispGpsUltUb and its DTO DispGpsUltUbDTO.
 */
@Mapper(componentModel = "spring", uses = {DispositivoGpsMapper.class, UltimaUbicacionMapper.class})
public interface DispGpsUltUbMapper extends EntityMapper<DispGpsUltUbDTO, DispGpsUltUb> {

    @Mapping(source = "dispositivoGps.id", target = "dispositivoGpsId")
    @Mapping(source = "dispositivoGps.idDispositivo", target = "dispositivoGpsIdDispositivo")
    @Mapping(source = "ultimaUbicacion.id", target = "ultimaUbicacionId")
    DispGpsUltUbDTO toDto(DispGpsUltUb dispGpsUltUb); 

    @Mapping(source = "dispositivoGpsId", target = "dispositivoGps")
    @Mapping(source = "ultimaUbicacionId", target = "ultimaUbicacion")
    DispGpsUltUb toEntity(DispGpsUltUbDTO dispGpsUltUbDTO);

    default DispGpsUltUb fromId(Long id) {
        if (id == null) {
            return null;
        }
        DispGpsUltUb dispGpsUltUb = new DispGpsUltUb();
        dispGpsUltUb.setId(id);
        return dispGpsUltUb;
    }
}
