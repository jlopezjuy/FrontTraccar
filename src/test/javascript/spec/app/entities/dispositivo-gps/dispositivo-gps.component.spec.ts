/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispositivoGpsComponent } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.component';
import { DispositivoGpsService } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.service';
import { DispositivoGps } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.model';

describe('Component Tests', () => {

    describe('DispositivoGps Management Component', () => {
        let comp: DispositivoGpsComponent;
        let fixture: ComponentFixture<DispositivoGpsComponent>;
        let service: DispositivoGpsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispositivoGpsComponent],
                providers: [
                    DispositivoGpsService
                ]
            })
            .overrideTemplate(DispositivoGpsComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispositivoGpsComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispositivoGpsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new DispositivoGps(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.dispositivoGps[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
