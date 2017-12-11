/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FrontTraccarTestModule } from '../../../test.module';
import { UltimaUbicacionDialogComponent } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion-dialog.component';
import { UltimaUbicacionService } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.service';
import { UltimaUbicacion } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.model';

describe('Component Tests', () => {

    describe('UltimaUbicacion Management Dialog Component', () => {
        let comp: UltimaUbicacionDialogComponent;
        let fixture: ComponentFixture<UltimaUbicacionDialogComponent>;
        let service: UltimaUbicacionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [UltimaUbicacionDialogComponent],
                providers: [
                    UltimaUbicacionService
                ]
            })
            .overrideTemplate(UltimaUbicacionDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UltimaUbicacionDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UltimaUbicacionService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UltimaUbicacion(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.ultimaUbicacion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'ultimaUbicacionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new UltimaUbicacion();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.ultimaUbicacion = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'ultimaUbicacionListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
