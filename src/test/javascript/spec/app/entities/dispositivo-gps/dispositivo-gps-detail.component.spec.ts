/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispositivoGpsDetailComponent } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps-detail.component';
import { DispositivoGpsService } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.service';
import { DispositivoGps } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.model';

describe('Component Tests', () => {

    describe('DispositivoGps Management Detail Component', () => {
        let comp: DispositivoGpsDetailComponent;
        let fixture: ComponentFixture<DispositivoGpsDetailComponent>;
        let service: DispositivoGpsService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispositivoGpsDetailComponent],
                providers: [
                    DispositivoGpsService
                ]
            })
            .overrideTemplate(DispositivoGpsDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispositivoGpsDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispositivoGpsService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new DispositivoGps(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.dispositivoGps).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
