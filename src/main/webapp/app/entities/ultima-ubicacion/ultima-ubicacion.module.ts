import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontTraccarSharedModule } from '../../shared';
import {
    UltimaUbicacionService,
    UltimaUbicacionPopupService,
    UltimaUbicacionComponent,
    UltimaUbicacionDetailComponent,
    UltimaUbicacionDialogComponent,
    UltimaUbicacionPopupComponent,
    UltimaUbicacionDeletePopupComponent,
    UltimaUbicacionDeleteDialogComponent,
    ultimaUbicacionRoute,
    ultimaUbicacionPopupRoute,
    UltimaUbicacionResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...ultimaUbicacionRoute,
    ...ultimaUbicacionPopupRoute,
];

@NgModule({
    imports: [
        FrontTraccarSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        UltimaUbicacionComponent,
        UltimaUbicacionDetailComponent,
        UltimaUbicacionDialogComponent,
        UltimaUbicacionDeleteDialogComponent,
        UltimaUbicacionPopupComponent,
        UltimaUbicacionDeletePopupComponent,
    ],
    entryComponents: [
        UltimaUbicacionComponent,
        UltimaUbicacionDialogComponent,
        UltimaUbicacionPopupComponent,
        UltimaUbicacionDeleteDialogComponent,
        UltimaUbicacionDeletePopupComponent,
    ],
    providers: [
        UltimaUbicacionService,
        UltimaUbicacionPopupService,
        UltimaUbicacionResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontTraccarUltimaUbicacionModule {}
