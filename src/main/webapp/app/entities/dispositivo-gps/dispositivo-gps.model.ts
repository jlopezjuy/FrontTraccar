import { BaseEntity } from './../../shared';

export class DispositivoGps implements BaseEntity {
    constructor(
        public id?: number,
        public idDispositivo?: string,
        public fechaCreacion?: any,
    ) {
    }
}
