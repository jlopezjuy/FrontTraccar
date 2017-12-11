import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Response } from '@angular/http';

import { Observable } from 'rxjs/Rx';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UltimaUbicacion } from './ultima-ubicacion.model';
import { UltimaUbicacionPopupService } from './ultima-ubicacion-popup.service';
import { UltimaUbicacionService } from './ultima-ubicacion.service';

@Component({
    selector: 'jhi-ultima-ubicacion-dialog',
    templateUrl: './ultima-ubicacion-dialog.component.html'
})
export class UltimaUbicacionDialogComponent implements OnInit {

    ultimaUbicacion: UltimaUbicacion;
    isSaving: boolean;
    fechaDp: any;

    constructor(
        public activeModal: NgbActiveModal,
        private ultimaUbicacionService: UltimaUbicacionService,
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
        if (this.ultimaUbicacion.id !== undefined) {
            this.subscribeToSaveResponse(
                this.ultimaUbicacionService.update(this.ultimaUbicacion));
        } else {
            this.subscribeToSaveResponse(
                this.ultimaUbicacionService.create(this.ultimaUbicacion));
        }
    }

    private subscribeToSaveResponse(result: Observable<UltimaUbicacion>) {
        result.subscribe((res: UltimaUbicacion) =>
            this.onSaveSuccess(res), (res: Response) => this.onSaveError());
    }

    private onSaveSuccess(result: UltimaUbicacion) {
        this.eventManager.broadcast({ name: 'ultimaUbicacionListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-ultima-ubicacion-popup',
    template: ''
})
export class UltimaUbicacionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ultimaUbicacionPopupService: UltimaUbicacionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.ultimaUbicacionPopupService
                    .open(UltimaUbicacionDialogComponent as Component, params['id']);
            } else {
                this.ultimaUbicacionPopupService
                    .open(UltimaUbicacionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
