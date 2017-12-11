import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { DispGpsUltUb } from './disp-gps-ult-ub.model';
import { DispGpsUltUbPopupService } from './disp-gps-ult-ub-popup.service';
import { DispGpsUltUbService } from './disp-gps-ult-ub.service';
import { DispositivoGps, DispositivoGpsService } from '../dispositivo-gps';
import { UltimaUbicacion, UltimaUbicacionService } from '../ultima-ubicacion';
import { ResponseWrapper } from '../../shared';

@Component({
    selector: 'jhi-disp-gps-ult-ub-dialog',
    templateUrl: './disp-gps-ult-ub-dialog.component.html'
})
export class DispGpsUltUbDialogComponent implements OnInit {

    dispGpsUltUb: DispGpsUltUb;
    isSaving: boolean;

    dispositivogps: DispositivoGps[];

    ultimaubicacions: UltimaUbicacion[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private dispGpsUltUbService: DispGpsUltUbService,
        private dispositivoGpsService: DispositivoGpsService,
        private ultimaUbicacionService: UltimaUbicacionService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.dispositivoGpsService.query()
            .subscribe((res: ResponseWrapper) => { this.dispositivogps = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
        this.ultimaUbicacionService.query()
            .subscribe((res: ResponseWrapper) => { this.ultimaubicacions = res.json; }, (res: ResponseWrapper) => this.onError(res.json));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.dispGpsUltUb.id !== undefined) {
            this.subscribeToSaveResponse(
                this.dispGpsUltUbService.update(this.dispGpsUltUb));
        } else {
            this.subscribeToSaveResponse(
                this.dispGpsUltUbService.create(this.dispGpsUltUb));
        }
    }

    private subscribeToSaveResponse(result: Observable<DispGpsUltUb>) {
        result.subscribe((res: DispGpsUltUb) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: DispGpsUltUb) {
        this.eventManager.broadcast({ name: 'dispGpsUltUbListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackDispositivoGpsById(index: number, item: DispositivoGps) {
        return item.id;
    }

    trackUltimaUbicacionById(index: number, item: UltimaUbicacion) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-disp-gps-ult-ub-popup',
    template: ''
})
export class DispGpsUltUbPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dispGpsUltUbPopupService: DispGpsUltUbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.dispGpsUltUbPopupService
                    .open(DispGpsUltUbDialogComponent as Component, params['id']);
            } else {
                this.dispGpsUltUbPopupService
                    .open(DispGpsUltUbDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
