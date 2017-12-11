/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispGpsUltUbComponent } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.component';
import { DispGpsUltUbService } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.service';
import { DispGpsUltUb } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.model';

describe('Component Tests', () => {

    describe('DispGpsUltUb Management Component', () => {
        let comp: DispGpsUltUbComponent;
        let fixture: ComponentFixture<DispGpsUltUbComponent>;
        let service: DispGpsUltUbService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispGpsUltUbComponent],
                providers: [
                    DispGpsUltUbService
                ]
            })
            .overrideTemplate(DispGpsUltUbComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispGpsUltUbComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispGpsUltUbService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new DispGpsUltUb(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.dispGpsUltUbs[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
