import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { SERVER_API_URL } from '../../app.constants';

import { DispGpsUltUb } from './disp-gps-ult-ub.model';
import { ResponseWrapper, createRequestOption } from '../../shared';

@Injectable()
export class DispGpsUltUbService {

    private resourceUrl = SERVER_API_URL + 'api/disp-gps-ult-ubs';

    constructor(private http: Http) { }

    create(dispGpsUltUb: DispGpsUltUb): Observable<DispGpsUltUb> {
        const copy = this.convert(dispGpsUltUb);
        return this.http.post(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    update(dispGpsUltUb: DispGpsUltUb): Observable<DispGpsUltUb> {
        const copy = this.convert(dispGpsUltUb);
        return this.http.put(this.resourceUrl, copy).map((res: Response) => {
            const jsonResponse = res.json();
            return this.convertItemFromServer(jsonResponse);
        });
    }

    find(id: number): Observable<DispGpsUltUb> {
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
     * Convert a returned JSON object to DispGpsUltUb.
     */
    private convertItemFromServer(json: any): DispGpsUltUb {
        const entity: DispGpsUltUb = Object.assign(new DispGpsUltUb(), json);
        return entity;
    }

    /**
     * Convert a DispGpsUltUb to a JSON which can be sent to the server.
     */
    private convert(dispGpsUltUb: DispGpsUltUb): DispGpsUltUb {
        const copy: DispGpsUltUb = Object.assign({}, dispGpsUltUb);
        return copy;
    }
}
