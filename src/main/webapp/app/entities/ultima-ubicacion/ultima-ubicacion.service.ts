import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { UltimaUbicacion } from './ultima-ubicacion.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class UltimaUbicacionService {

    private resourceUrl = SERVER_API_URL + 'api/ultima-ubicacions';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(ultimaUbicacion: UltimaUbicacion): Observable<UltimaUbicacion> {
        const copy = this.convert(ultimaUbicacion);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(ultimaUbicacion: UltimaUbicacion): Observable<UltimaUbicacion> {
        const copy = this.convert(ultimaUbicacion);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<UltimaUbicacion> {
        return this.http.get(`${this.resourceUrl}/${id}`).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    query(req?: any): Observable<ResponseWrapper> {
        const options = createRequestOption(req);
        return this.http.get(this.resourceUrl, options)
            .map((res: Response) => this.convertResponse(res));
    }

    delete(id: number): Observable<Response> {
        return this.http.delete(`${this.resourceUrl}/${id}`);
    }

    private convertResponse(res: Response): ResponseWrapper {
        const jsonResponse = res.json();
        const result = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            result.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return new ResponseWrapper(res.headers, result, res.status);
    }

    /**
     * Convert a returned JSON object to UltimaUbicacion.
     */
    private convertItemFromServer(json: any): UltimaUbicacion {
        const entity: UltimaUbicacion = Object.assign(new UltimaUbicacion(), json);
        entity.fecha = this.dateUtils
            .convertLocalDateFromServer(json.fecha);
        return entity;
    }

    /**
     * Convert a UltimaUbicacion to a JSON which can be sent to the server.
     */
    private convert(ultimaUbicacion: UltimaUbicacion): UltimaUbicacion {
        const copy: UltimaUbicacion = Object.assign({}, ultimaUbicacion);
        copy.fecha = this.dateUtils
            .convertLocalDateToServer(ultimaUbicacion.fecha);
        return copy;
    }
}
