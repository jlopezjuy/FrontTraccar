import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DispGpsUltUb } from './disp-gps-ult-ub.model';
import { DispGpsUltUbPopupService } from './disp-gps-ult-ub-popup.service';
import { DispGpsUltUbService } from './disp-gps-ult-ub.service';

@Component({
    selector: 'jhi-disp-gps-ult-ub-delete-dialog',
    templateUrl: './disp-gps-ult-ub-delete-dialog.component.html'
})
export class DispGpsUltUbDeleteDialogComponent {

    dispGpsUltUb: DispGpsUltUb;

    constructor(
        private dispGpsUltUbService: DispGpsUltUbService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dispGpsUltUbService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'dispGpsUltUbListModification',
                content: 'Deleted an dispGpsUltUb'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-disp-gps-ult-ub-delete-popup',
    template: ''
})
export class DispGpsUltUbDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dispGpsUltUbPopupService: DispGpsUltUbPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.dispGpsUltUbPopupService
                .open(DispGpsUltUbDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
