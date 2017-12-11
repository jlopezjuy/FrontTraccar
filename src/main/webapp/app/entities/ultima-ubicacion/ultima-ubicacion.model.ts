import { BaseEntity } from './../../shared';

export class UltimaUbicacion implements BaseEntity {
    constructor(
        public id?: number,
        public latitud?: string,
        public longitud?: string,
        public altitud?: string,
        public velocidad?: string,
        public curso?: string,
        public direccion?: string,
        public fecha?: any,
    ) {
    }
}
