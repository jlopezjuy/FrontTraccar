import {Component, OnInit} from '@angular/core';

declare var google: any;
declare var $: any;

@Component({
    selector: 'jhi-map',
    templateUrl: './map.component.html',
    styles: []
})
export class MapComponent implements OnInit {
    mapProp: any;
    map: any;
    marker: any;
    position = true;

    constructor() {
    }

    /**
     * Agregar metodo para mostrar todas las posiciones de una radio
     */
    ngOnInit() {
        const positionInitial = {lat: -24.203526, lng: -65.297196};
        this.mapProp = {
            center: new google.maps.LatLng(-24.203526, -65.297196),
            zoom: 18,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        const el = document.getElementById('gmap');
        this.map = new google.maps.Map(el, this.mapProp);
        const initial = {
            lat: -24.203526,
            lng: -65.297196
        };
        this.marker = new google.maps.Marker({
            position: initial,
            map: this.map
        });
        this.marker = new google.maps.Marker({
            position: positionInitial,
            map: this.map,
            icon: 'http://maps.google.com/mapfiles/ms/icons/yellow-dot.png'
        });
    }

}
