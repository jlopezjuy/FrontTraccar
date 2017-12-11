import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { DispositivoGpsComponent } from './dispositivo-gps.component';
import { DispositivoGpsDetailComponent } from './dispositivo-gps-detail.component';
import { DispositivoGpsPopupComponent } from './dispositivo-gps-dialog.component';
import { DispositivoGpsDeletePopupComponent } from './dispositivo-gps-delete-dialog.component';

@Injectable()
export class DispositivoGpsResolvePagingParams implements Resolve<any> {

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

export const dispositivoGpsRoute: Routes = [
    {
        path: 'dispositivo-gps',
        component: DispositivoGpsComponent,
        resolve: {
            'pagingParams': DispositivoGpsResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispositivoGps.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'dispositivo-gps/:id',
        component: DispositivoGpsDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispositivoGps.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dispositivoGpsPopupRoute: Routes = [
    {
        path: 'dispositivo-gps-new',
        component: DispositivoGpsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispositivoGps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'dispositivo-gps/:id/edit',
        component: DispositivoGpsPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispositivoGps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'dispositivo-gps/:id/delete',
        component: DispositivoGpsDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.dispositivoGps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
