/* tslint:disable max-line-length */
import { TestBed, async } from '@angular/core/testing';
import { MockBackend } from '@angular/http/testing';
import { ConnectionBackend, RequestOptions, BaseRequestOptions, Http, Response, ResponseOptions } from '@angular/http';
import { JhiDateUtils } from 'ng-jhipster';

import { DispGpsUltUbService } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.service';
import { DispGpsUltUb } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.model';

describe('Service Tests', () => {

    describe('DispGpsUltUb Service', () => {
        let service: DispGpsUltUbService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                providers: [
                    {
                        provide: ConnectionBackend,
                        useClass: MockBackend
                    },
                    {
                        provide: RequestOptions,
                        useClass: BaseRequestOptions
                    },
                    Http,
                    JhiDateUtils,
                    DispGpsUltUbService
                ]
            });

            service = TestBed.get(DispGpsUltUbService);

            this.backend = TestBed.get(ConnectionBackend) as MockBackend;
            this.backend.connections.subscribe((connection: any) => {
                this.lastConnection = connection;
            });
        }));

        describe('Service methods', () => {
            it('should call correct URL', () => {
                service.find(123).subscribe(() => {});

                expect(this.lastConnection).toBeDefined();
                expect(this.lastConnection.request.url).toEqual('api/disp-gps-ult-ubs/' + 123);
            });
            it('should return DispGpsUltUb', () => {

                let entity: DispGpsUltUb;
                service.find(123).subscribe((_entity: DispGpsUltUb) => {
                    entity = _entity;
                });

                this.lastConnection.mockRespond(new Response(new ResponseOptions({
                    body: JSON.stringify({id: 123}),
                })));

                expect(entity).toBeDefined();
                expect(entity.id).toEqual(123);
            });

            it('should propagate not found response', () => {

                let error: any;
                service.find(123).subscribe(null, (_error: any) => {
                    error = _error;
                });

                this.lastConnection.mockError(new Response(new ResponseOptions({
                    status: 404,
                })));

                expect(error).toBeDefined();
                expect(error.status).toEqual(404);
            });
        });
    });

});
