import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UltimaUbicacion } from './ultima-ubicacion.model';
import { UltimaUbicacionPopupService } from './ultima-ubicacion-popup.service';
import { UltimaUbicacionService } from './ultima-ubicacion.service';

@Component({
    selector: 'jhi-ultima-ubicacion-delete-dialog',
    templateUrl: './ultima-ubicacion-delete-dialog.component.html'
})
export class UltimaUbicacionDeleteDialogComponent {

    ultimaUbicacion: UltimaUbicacion;

    constructor(
        private ultimaUbicacionService: UltimaUbicacionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.ultimaUbicacionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'ultimaUbicacionListModification',
                content: 'Deleted an ultimaUbicacion'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ultima-ubicacion-delete-popup',
    template: ''
})
export class UltimaUbicacionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private ultimaUbicacionPopupService: UltimaUbicacionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.ultimaUbicacionPopupService
                .open(UltimaUbicacionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
