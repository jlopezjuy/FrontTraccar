/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispGpsUltUbDetailComponent } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub-detail.component';
import { DispGpsUltUbService } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.service';
import { DispGpsUltUb } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.model';

describe('Component Tests', () => {

    describe('DispGpsUltUb Management Detail Component', () => {
        let comp: DispGpsUltUbDetailComponent;
        let fixture: ComponentFixture<DispGpsUltUbDetailComponent>;
        let service: DispGpsUltUbService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispGpsUltUbDetailComponent],
                providers: [
                    DispGpsUltUbService
                ]
            })
            .overrideTemplate(DispGpsUltUbDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispGpsUltUbDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispGpsUltUbService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new DispGpsUltUb(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.dispGpsUltUb).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
