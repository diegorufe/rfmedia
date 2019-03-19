import { Directive, Input, TemplateRef } from "@angular/core";

/**
 * This class is for use in html <ng-template>
 */
@Directive({
    selector: '[rfTemplate]',
    host: {
    }
})
export class TemplateDirective {
    
    @Input() type: string;
    
    @Input('rfTemplate') name: string;
    
    constructor(public template: TemplateRef<any>) {}
    
    getType(): string {
        return this.name;
    }
}