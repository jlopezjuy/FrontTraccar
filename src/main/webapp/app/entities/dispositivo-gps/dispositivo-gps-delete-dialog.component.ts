import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DispositivoGps } from './dispositivo-gps.model';
import { DispositivoGpsPopupService } from './dispositivo-gps-popup.service';
import { DispositivoGpsService } from './dispositivo-gps.service';

@Component({
    selector: 'jhi-dispositivo-gps-delete-dialog',
    templateUrl: './dispositivo-gps-delete-dialog.component.html'
})
export class DispositivoGpsDeleteDialogComponent {

    dispositivoGps: DispositivoGps;

    constructor(
        private dispositivoGpsService: DispositivoGpsService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dispositivoGpsService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'dispositivoGpsListModification',
                content: 'Deleted an dispositivoGps'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-dispositivo-gps-delete-popup',
    template: ''
})
export class DispositivoGpsDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dispositivoGpsPopupService: DispositivoGpsPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.dispositivoGpsPopupService
                .open(DispositivoGpsDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
