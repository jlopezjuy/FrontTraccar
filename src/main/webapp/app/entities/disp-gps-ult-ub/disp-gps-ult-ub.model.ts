import { BaseEntity } from './../../shared';

export class DispGpsUltUb implements BaseEntity {
    constructor(
        public id?: number,
        public dispositivoGpsId?: number,
        public ultimaUbicacionId?: number,
    ) {
    }
}
