import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontTraccarSharedModule } from '../../shared';
import {
    DispositivoGpsService,
    DispositivoGpsPopupService,
    DispositivoGpsComponent,
    DispositivoGpsDetailComponent,
    DispositivoGpsDialogComponent,
    DispositivoGpsPopupComponent,
    DispositivoGpsDeletePopupComponent,
    DispositivoGpsDeleteDialogComponent,
    dispositivoGpsRoute,
    dispositivoGpsPopupRoute,
    DispositivoGpsResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...dispositivoGpsRoute,
    ...dispositivoGpsPopupRoute,
];

@NgModule({
    imports: [
        FrontTraccarSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DispositivoGpsComponent,
        DispositivoGpsDetailComponent,
        DispositivoGpsDialogComponent,
        DispositivoGpsDeleteDialogComponent,
        DispositivoGpsPopupComponent,
        DispositivoGpsDeletePopupComponent,
    ],
    entryComponents: [
        DispositivoGpsComponent,
        DispositivoGpsDialogComponent,
        DispositivoGpsPopupComponent,
        DispositivoGpsDeleteDialogComponent,
        DispositivoGpsDeletePopupComponent,
    ],
    providers: [
        DispositivoGpsService,
        DispositivoGpsPopupService,
        DispositivoGpsResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontTraccarDispositivoGpsModule {}
