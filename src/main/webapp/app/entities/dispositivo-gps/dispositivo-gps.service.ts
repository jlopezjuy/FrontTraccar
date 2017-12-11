import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { DispositivoGps } from './dispositivo-gps.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DispositivoGpsService {

    private resourceUrl = SERVER_API_URL + 'api/dispositivo-gps';

    constructor(private http: Http, private dateUtils: JhiDateUtils) { }

    create(dispositivoGps: DispositivoGps): Observable<DispositivoGps> {
        const copy = this.convert(dispositivoGps);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(dispositivoGps: DispositivoGps): Observable<DispositivoGps> {
        const copy = this.convert(dispositivoGps);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<DispositivoGps> {
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
     * Convert a returned JSON object to DispositivoGps.
     */
    private convertItemFromServer(json: any): DispositivoGps {
        const entity: DispositivoGps = Object.assign(new DispositivoGps(), json);
        entity.fechaCreacion = this.dateUtils
            .convertLocalDateFromServer(json.fechaCreacion);
        return entity;
    }

    /**
     * Convert a DispositivoGps to a JSON which can be sent to the server.
     */
    private convert(dispositivoGps: DispositivoGps): DispositivoGps {
        const copy: DispositivoGps = Object.assign({}, dispositivoGps);
        copy.fechaCreacion = this.dateUtils
            .convertLocalDateToServer(dispositivoGps.fechaCreacion);
        return copy;
    }
}
