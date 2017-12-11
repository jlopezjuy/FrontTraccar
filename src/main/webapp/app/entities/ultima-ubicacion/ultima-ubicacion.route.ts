import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { UltimaUbicacionComponent } from './ultima-ubicacion.component';
import { UltimaUbicacionDetailComponent } from './ultima-ubicacion-detail.component';
import { UltimaUbicacionPopupComponent } from './ultima-ubicacion-dialog.component';
import { UltimaUbicacionDeletePopupComponent } from './ultima-ubicacion-delete-dialog.component';

@Injectable()
export class UltimaUbicacionResolvePagingParams implements Resolve<any> {

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

export const ultimaUbicacionRoute: Routes = [
    {
        path: 'ultima-ubicacion',
        component: UltimaUbicacionComponent,
        resolve: {
            'pagingParams': UltimaUbicacionResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.ultimaUbicacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'ultima-ubicacion/:id',
        component: UltimaUbicacionDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.ultimaUbicacion.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const ultimaUbicacionPopupRoute: Routes = [
    {
        path: 'ultima-ubicacion-new',
        component: UltimaUbicacionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.ultimaUbicacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ultima-ubicacion/:id/edit',
        component: UltimaUbicacionPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.ultimaUbicacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'ultima-ubicacion/:id/delete',
        component: UltimaUbicacionDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'frontTraccarApp.ultimaUbicacion.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
