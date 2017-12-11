/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FrontTraccarTestModule } from '../../../test.module';
import { UltimaUbicacionDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion-delete-dialog.component';
import { UltimaUbicacionService } from '../../../../../../main/webapp/app/entities/ultima-ubicacion/ultima-ubicacion.service';

describe('Component Tests', () => {

    describe('UltimaUbicacion Management Delete Component', () => {
        let comp: UltimaUbicacionDeleteDialogComponent;
        let fixture: ComponentFixture<UltimaUbicacionDeleteDialogComponent>;
        let service: UltimaUbicacionService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [UltimaUbicacionDeleteDialogComponent],
                providers: [
                    UltimaUbicacionService
                ]
            })
            .overrideTemplate(UltimaUbicacionDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(UltimaUbicacionDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(UltimaUbicacionService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(Observable.of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
