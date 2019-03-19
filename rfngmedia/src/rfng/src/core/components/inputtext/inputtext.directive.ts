import { Directive, ElementRef, Optional } from "@angular/core";
import { NgModel } from "@angular/forms";

@Directive({
    selector: '[rfInputText]'
})
export class InputTextDirective{
    constructor(public el: ElementRef, @Optional() public ngModel: NgModel) {}
}