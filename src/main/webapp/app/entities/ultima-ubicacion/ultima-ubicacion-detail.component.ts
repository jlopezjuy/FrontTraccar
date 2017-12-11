import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { UltimaUbicacion } from './ultima-ubicacion.model';
import { UltimaUbicacionService } from './ultima-ubicacion.service';

@Component({
    selector: 'jhi-ultima-ubicacion-detail',
    templateUrl: './ultima-ubicacion-detail.component.html'
})
export class UltimaUbicacionDetailComponent implements OnInit, OnDestroy {

    ultimaUbicacion: UltimaUbicacion;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private ultimaUbicacionService: UltimaUbicacionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUltimaUbicacions();
    }

    load(id) {
        this.ultimaUbicacionService.find(id).subscribe((ultimaUbicacion) => {
            this.ultimaUbicacion = ultimaUbicacion;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUltimaUbicacions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'ultimaUbicacionListModification',
            (response) => this.load(this.ultimaUbicacion.id)
        );
    }
}
