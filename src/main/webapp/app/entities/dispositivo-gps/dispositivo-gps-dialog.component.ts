import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DispositivoGps } from './dispositivo-gps.model';
import { DispositivoGpsPopupService } from './dispositivo-gps-popup.service';
import { DispositivoGpsService } from './dispositivo-gps.service';

@Component({
    selector: 'jhi-dispositivo-gps-dialog',
    templateUrl: './dispositivo-gps-dialog.component.html'
})
export class DispositivoGpsDialogComponent implements OnInit {

    dispositivoGps: DispositivoGps;
    isSaving: boolean;
    fechaCreacionDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private dispositivoGpsService: DispositivoGpsService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.dispositivoGps.id !== undefined) {
            this.subscribeToSaveResponse(
                this.dispositivoGpsService.update(this.dispositivoGps));
        } else {
            this.subscribeToSaveResponse(
                this.dispositivoGpsService.create(this.dispositivoGps));
        }
    }

    private subscribeToSaveResponse(result: Observable<DispositivoGps>) {
        result.subscribe((res: DispositivoGps) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: DispositivoGps) {
        this.eventManager.broadcast({ name: 'dispositivoGpsListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-dispositivo-gps-popup',
    template: ''
})
export class DispositivoGpsPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dispositivoGpsPopupService: DispositivoGpsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.dispositivoGpsPopupService
                    .open(DispositivoGpsDialogComponent as Component, params['id']);
            } else {
                this.dispositivoGpsPopupService
                    .open(DispositivoGpsDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
