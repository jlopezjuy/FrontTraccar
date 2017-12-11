import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { DispGpsUltUb } from './disp-gps-ult-ub.model';
import { DispGpsUltUbService } from './disp-gps-ult-ub.service';

@Injectable()
export class DispGpsUltUbPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private dispGpsUltUbService: DispGpsUltUbService

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
                this.dispGpsUltUbService.find(id).subscribe((dispGpsUltUb) => {
                    this.ngbModalRef = this.dispGpsUltUbModalRef(component, dispGpsUltUb);
                    resolve(this.ngbModalRef);
                });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.dispGpsUltUbModalRef(component, new DispGpsUltUb());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    dispGpsUltUbModalRef(component: Component, dispGpsUltUb: DispGpsUltUb): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.dispGpsUltUb = dispGpsUltUb;
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
