/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Rx';
import { Headers } from '@angular/http';

import { FrontTraccarTestModule } from '../../../test.module';
import { UltimaUbicacionComponent } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.component';
import { UltimaUbicacionService } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.service';
import { UltimaUbicacion } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.model';

describe('Component Tests', () => {

    describe('UltimaUbicacion Management Component', () => {
        let comp: UltimaUbicacionComponent;
        let fixture: ComponentFixture<UltimaUbicacionComponent>;
        let service: UltimaUbicacionService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [UltimaUbicacionComponent],
                providers: [
                    UltimaUbicacionService
                ]
            })
            .overrideTemplate(UltimaUbicacionComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UltimaUbicacionComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UltimaUbicacionService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new Headers();
                headers.append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of({
                    json: [new UltimaUbicacion(123)],
                    headers
                }));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.ultimaUbicacions[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
