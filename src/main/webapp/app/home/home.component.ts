import { Component, OnInit } from '@angular/core';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Account, LoginModalService, Principal } from '../shared';

declare var google: any;
declare var $: any;

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: [
        'home.css'
    ]

})
export class HomeComponent implements OnInit {
    account: Account;
    modalRef: NgbModalRef;

    mapProp: any;
    map: any;
    marker: any;
    position: boolean = true;

    constructor(
        private principal: Principal,
        private loginModalService: LoginModalService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.principal.identity().then((account) => {
            this.account = account;
        });
        this.registerAuthenticationSuccess();

        let uluru = { lat: -24.203526, lng: -65.297196 };
        this.mapProp = {
            center: new google.maps.LatLng(-24.203526, -65.297196),
            zoom: 18,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        let el = document.getElementById('gmap');
        this.map = new google.maps.Map(el, this.mapProp);
        let inicial = {
            lat: -24.203526,
            lng: -65.297196
        };
        this.marker = new google.maps.Marker({
            position: inicial,
            map: this.map
        });
        this.marker = new google.maps.Marker({
            position: uluru,
            map: this.map,
            icon: 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png'
        });
    }

    registerAuthenticationSuccess() {
        this.eventManager.subscribe('authenticationSuccess', (message) => {
            this.principal.identity().then((account) => {
                this.account = account;
            });
        });
    }

    isAuthenticated() {
        return this.principal.isAuthenticated();
    }

    login() {
        this.modalRef = this.loginModalService.open();
    }
}
