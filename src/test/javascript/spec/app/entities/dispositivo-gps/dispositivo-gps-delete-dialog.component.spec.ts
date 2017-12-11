/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispositivoGpsDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps-delete-dialog.component';
import { DispositivoGpsService } from '../../../../../../main/webapp/app/entities/dispositivo-gps/dispositivo-gps.service';

describe('Component Tests', () => {

    describe('DispositivoGps Management Delete Component', () => {
        let comp: DispositivoGpsDeleteDialogComponent;
        let fixture: ComponentFixture<DispositivoGpsDeleteDialogComponent>;
        let service: DispositivoGpsService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispositivoGpsDeleteDialogComponent],
                providers: [
                    DispositivoGpsService
                ]
            })
            .overrideTemplate(DispositivoGpsDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispositivoGpsDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispositivoGpsService);
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
