import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { DispositivoGps } from './dispositivo-gps.model';
import { DispositivoGpsService } from './dispositivo-gps.service';

@Component({
    selector: 'jhi-dispositivo-gps-detail',
    templateUrl: './dispositivo-gps-detail.component.html'
})
export class DispositivoGpsDetailComponent implements OnInit, OnDestroy {

    dispositivoGps: DispositivoGps;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dispositivoGpsService: DispositivoGpsService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDispositivoGps();
    }

    load(id) {
        this.dispositivoGpsService.find(id).subscribe((dispositivoGps) => {
            this.dispositivoGps = dispositivoGps;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDispositivoGps() {
        this.eventSubscriber = this.eventManager.subscribe(
            'dispositivoGpsListModification',
            (response) => this.load(this.dispositivoGps.id)
        );
    }
}
