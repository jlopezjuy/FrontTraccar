import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { DispGpsUltUb } from './disp-gps-ult-ub.model';
import { DispGpsUltUbService } from './disp-gps-ult-ub.service';

@Component({
    selector: 'jhi-disp-gps-ult-ub-detail',
    templateUrl: './disp-gps-ult-ub-detail.component.html'
})
export class DispGpsUltUbDetailComponent implements OnInit, OnDestroy {

    dispGpsUltUb: DispGpsUltUb;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dispGpsUltUbService: DispGpsUltUbService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDispGpsUltUbs();
    }

    load(id) {
        this.dispGpsUltUbService.find(id).subscribe((dispGpsUltUb) => {
            this.dispGpsUltUb = dispGpsUltUb;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDispGpsUltUbs() {
        this.eventSubscriber = this.eventManager.subscribe(
            'dispGpsUltUbListModification',
            (response) => this.load(this.dispGpsUltUb.id)
        );
    }
}
