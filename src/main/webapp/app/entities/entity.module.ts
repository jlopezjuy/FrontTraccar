import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { FrontTraccarDispositivoGpsModule } from './dispositivo-gps/dispositivo-gps.module';
import { FrontTraccarUltimaUbicacionModule } from './ultima-ubicacion/ultima-ubicacion.module';
import { FrontTraccarDispGpsUltUbModule } from './disp-gps-ult-ub/disp-gps-ult-ub.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        FrontTraccarDispositivoGpsModule,
        FrontTraccarUltimaUbicacionModule,
        FrontTraccarDispGpsUltUbModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontTraccarEntityModule {}
