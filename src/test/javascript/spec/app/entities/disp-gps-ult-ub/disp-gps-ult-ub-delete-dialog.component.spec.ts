/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { FrontTraccarTestModule } from '../../../test.module';
import { DispGpsUltUbDeleteDialogComponent } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub-delete-dialog.component';
import { DispGpsUltUbService } from '../../../../../../main/webapp/app/entities/disp-gps-ult-ub/disp-gps-ult-ub.service';

describe('Component Tests', () => {

    describe('DispGpsUltUb Management Delete Component', () => {
        let comp: DispGpsUltUbDeleteDialogComponent;
        let fixture: ComponentFixture<DispGpsUltUbDeleteDialogComponent>;
        let service: DispGpsUltUbService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [FrontTraccarTestModule],
                declarations: [DispGpsUltUbDeleteDialogComponent],
                providers: [
                    DispGpsUltUbService
                ]
            })
            .overrideTemplate(DispGpsUltUbDeleteDialogComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(DispGpsUltUbDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(DispGpsUltUbService);
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
