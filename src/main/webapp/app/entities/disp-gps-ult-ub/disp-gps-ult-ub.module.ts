import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { FrontTraccarSharedModule } from '../../shared';
import {
    DispGpsUltUbService,
    DispGpsUltUbPopupService,
    DispGpsUltUbComponent,
    DispGpsUltUbDetailComponent,
    DispGpsUltUbDialogComponent,
    DispGpsUltUbPopupComponent,
    DispGpsUltUbDeletePopupComponent,
    DispGpsUltUbDeleteDialogComponent,
    dispGpsUltUbRoute,
    dispGpsUltUbPopupRoute,
    DispGpsUltUbResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...dispGpsUltUbRoute,
    ...dispGpsUltUbPopupRoute,
];

@NgModule({
    imports: [
        FrontTraccarSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DispGpsUltUbComponent,
        DispGpsUltUbDetailComponent,
        DispGpsUltUbDialogComponent,
        DispGpsUltUbDeleteDialogComponent,
        DispGpsUltUbPopupComponent,
        DispGpsUltUbDeletePopupComponent,
    ],
    entryComponents: [
        DispGpsUltUbComponent,
        DispGpsUltUbDialogComponent,
        DispGpsUltUbPopupComponent,
        DispGpsUltUbDeleteDialogComponent,
        DispGpsUltUbDeletePopupComponent,
    ],
    providers: [
        DispGpsUltUbService,
        DispGpsUltUbPopupService,
        DispGpsUltUbResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class FrontTraccarDispGpsUltUbModule {}
