/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispGpsUltUbDialogComponent } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub-dialog.component';
import { DispGpsUltUbService } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.service';
import { DispGpsUltUb } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.model';
import { DispositivoGpsService } from '../../../../../../main/webapp/app/entities/dispositivo-gps';
import { UltimaUbicacionService } from '../../../../../../main/webapp/app/entities/ultima-ubicacion';

describe('Component Tests', () => {

    describe('DispGpsUltUb Management Dialog Component', () => {
        let comp: DispGpsUltUbDialogComponent;
        let fixture: ComponentFixture<DispGpsUltUbDialogComponent>;
        let service: DispGpsUltUbService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispGpsUltUbDialogComponent],
                providers: [
                    DispositivoGpsService,
                    UltimaUbicacionService,
                    DispGpsUltUbService
                ]
            })
            .overrideTemplate(DispGpsUltUbDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispGpsUltUbDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispGpsUltUbService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new DispGpsUltUb(123);
                        spyOn(service, 'update').and.returnValue(Observable.of(entity));
                        comp.dispGpsUltUb = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.update).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'dispGpsUltUbListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );

            it('Should call create service on save for new entity',
                inject([],
                    fakeAsync(() => {
                        // GIVEN
                        const entity = new DispGpsUltUb();
                        spyOn(service, 'create').and.returnValue(Observable.of(entity));
                        comp.dispGpsUltUb = entity;
                        // WHEN
                        comp.save();
                        tick(); // simulate async

                        // THEN
                        expect(service.create).toHaveBeenCalledWith(entity);
                        expect(comp.isSaving).toEqual(false);
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalledWith({ name: 'dispGpsUltUbListModification', content: 'OK'});
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });

});
