/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';

import { FrontTraccarTestModule } from '../../../test.module';
import { UltimaUbicacionDetailComponent } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion-detail.component';
import { UltimaUbicacionService } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.service';
import { UltimaUbicacion } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.model';

describe('Component Tests', () => {

    describe('UltimaUbicacion Management Detail Component', () => {
        let comp: UltimaUbicacionDetailComponent;
        let fixture: ComponentFixture<UltimaUbicacionDetailComponent>;
        let service: UltimaUbicacionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [UltimaUbicacionDetailComponent],
                providers: [
                    UltimaUbicacionService
                ]
            })
            .overrideTemplate(UltimaUbicacionDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UltimaUbicacionDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UltimaUbicacionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new UltimaUbicacion(123)));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.ultimaUbicacion).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
