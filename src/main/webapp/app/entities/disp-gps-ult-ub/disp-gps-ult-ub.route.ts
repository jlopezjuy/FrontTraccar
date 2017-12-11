import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { DispGpsUltUbComponent } from './disp-gps-ult-ub.component';
import { DispGpsUltUbDetailComponent } from './disp-gps-ult-ub-detail.component';
import { DispGpsUltUbPopupComponent } from './disp-gps-ult-ub-dialog.component';
import { DispGpsUltUbDeletePopupComponent } from './disp-gps-ult-ub-delete-dialog.component';

@Injectable()
export class DispGpsUltUbResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const dispGpsUltUbRoute: Routes = [
    {
        path: 'disp-gps-ult-ub',
        component: DispGpsUltUbComponent,
        resolve: {
            'pagingParams': DispGpsUltUbResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispGpsUltUb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'disp-gps-ult-ub/:id',
        component: DispGpsUltUbDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispGpsUltUb.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dispGpsUltUbPopupRoute: Routes = [
    {
        path: 'disp-gps-ult-ub-new',
        component: DispGpsUltUbPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispGpsUltUb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'disp-gps-ult-ub/:id/edit',
        component: DispGpsUltUbPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispGpsUltUb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'disp-gps-ult-ub/:id/delete',
        component: DispGpsUltUbDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispGpsUltUb.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
