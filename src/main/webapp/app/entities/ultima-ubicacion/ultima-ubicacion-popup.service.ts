import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { UltimaUbicacion } from './ultima-ubicacion.model';
import { UltimaUbicacionService } from './ultima-ubicacion.service';

@Injectable()
export class UltimaUbicacionPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private ultimaUbicacionService: UltimaUbicacionService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.ultimaUbicacionService.find(id).subscribe((ultimaUbicacion) => {
                    if (ultimaUbicacion.fecha) {
                        ultimaUbicacion.fecha = {
                            year: ultimaUbicacion.fecha.getFullYear(),
                            month: ultimaUbicacion.fecha.getMonth() + 1,
                            day: ultimaUbicacion.fecha.getDate()
                        };
                    }
                    this.ngbModalRef = this.ultimaUbicacionModalRef(component, ultimaUbicacion);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.ultimaUbicacionModalRef(component, new UltimaUbicacion());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    ultimaUbicacionModalRef(component: Component, ultimaUbicacion: UltimaUbicacion): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.ultimaUbicacion = ultimaUbicacion;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
