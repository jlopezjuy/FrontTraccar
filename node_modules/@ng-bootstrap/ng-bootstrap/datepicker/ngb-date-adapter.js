var __extends = (this && this.__extends) || function (d, b) {
    for (var p in b) if (b.hasOwnProperty(p)) d[p] = b[p];
    function __() { this.constructor = d; }
    d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
};
import { Injectable } from '@angular/core';
/**
 * Abstract type serving as a DI token for the service converting from your application Date model to internal
 * NgbDateStruct model.
 * A default implementation converting from and to NgbDateStruct is provided for retro-compatibility,
 * but you can provide another implementation to use an alternative format, ie for using with native Date Object.
 */
var NgbDateAdapter = (function () {
    function NgbDateAdapter() {
    }
    return NgbDateAdapter;
}());
export { NgbDateAdapter };
NgbDateAdapter.decorators = [
    { type: Injectable },
];
/** @nocollapse */
NgbDateAdapter.ctorParameters = function () { return []; };
var NgbDateStructAdapter = (function (_super) {
    __extends(NgbDateStructAdapter, _super);
    function NgbDateStructAdapter() {
        return _super !== null && _super.apply(this, arguments) || this;
    }
    /**
     * Converts a NgbDateStruct value into NgbDateStruct value
     * @param  {NgbDateStruct} value
     * @return {NgbDateStruct}
     */
    NgbDateStructAdapter.prototype.fromModel = function (date) {
        return date ? { year: date.year, month: date.month, day: date.day || 1 } : null;
    };
    /**
     * Converts a NgbDateStruct value into NgbDateStruct value
     * @param  {NgbDateStruct} value
     * @return {NgbDateStruct}
     */
    NgbDateStructAdapter.prototype.toModel = function (date) {
        return date ? { year: date.year, month: date.month, day: date.day || 1 } : null;
    };
    return NgbDateStructAdapter;
}(NgbDateAdapter));
export { NgbDateStructAdapter };
NgbDateStructAdapter.decorators = [
    { type: Injectable },
];
/** @nocollapse */
NgbDateStructAdapter.ctorParameters = function () { return []; };
//# sourceMappingURL=ngb-date-adapter.js.map