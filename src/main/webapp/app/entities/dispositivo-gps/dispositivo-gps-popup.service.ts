import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DispositivoGps } from './dispositivo-gps.model';
import { DispositivoGpsService } from './dispositivo-gps.service';

@Injectable()
export class DispositivoGpsPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private dispositivoGpsService: DispositivoGpsService

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
                this.dispositivoGpsService.find(id).subscribe((dispositivoGps) => {
                    if (dispositivoGps.fechaCreacion) {
                        dispositivoGps.fechaCreacion = {
                            year: dispositivoGps.fechaCreacion.getFullYear(),
                            month: dispositivoGps.fechaCreacion.getMonth() + 1,
                            day: dispositivoGps.fechaCreacion.getDate()
                        };
                    }
                    this.ngbModalRef = this.dispositivoGpsModalRef(component, dispositivoGps);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.dispositivoGpsModalRef(component, new DispositivoGps());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    dispositivoGpsModalRef(component: Component, dispositivoGps: DispositivoGps): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.dispositivoGps = dispositivoGps;
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
