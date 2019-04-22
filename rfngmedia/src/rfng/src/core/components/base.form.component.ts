import { BaseCoreComponent } from './base.core.component';
import { DynamicLoaderComponent } from './dynamic/dynamic.loader.component';
import { CoreUtils } from '../utils/core.utils';
/**
 * This class is a base class for extends all components
 */
export abstract class BaseFormComponent extends BaseCoreComponent {
    constructor() {
        super();
    }

    /** 
   * Clear all messages
  */
    clearMessages() {
        if (this.tabView != null && this.tabView != undefined && this.tabView.toastComponent != null && this.tabView.toastComponent != undefined) {
            this.tabView.toastComponent.closeAllMessages();
        }
    }

    addMsgSucces(detail, data?) {
        this.addMsgSuccesClear(detail, true);
    }

    /**
     * Método para añadir mensages de información 
     * @param summary 
     * @param detail 
     * @param data 
     */
    addMsgInfo(summary, detail, data?) {
        this.addMsgInfoClear(detail, true, data);
    }

    addMsgWarning(detail, data?) {
        this.addMsgWarningClear(detail, true, data);
    }

    /**
     * Método para añadir mensages de error 
     * @param summary 
     * @param detail 
     */
    addMsgError(detail, data?) {
        this.addMsgErrorClear(detail, true, data);
    }

    addMsgSuccesClear(detail, clear: boolean, data?) {
        this.addMsg('success', detail, clear, data);
    }


    addMsgInfoClear(detail, clear: boolean, data?) {
        this.addMsg('info', detail, clear, data);
    }

    addMsgWarningClear(detail, clear: boolean, data?) {
        this.addMsg('warn', detail, clear, data);
    }

    addMsgErrorClear(detail, clear: boolean, data?) {
        this.addMsg('error', detail, clear, data);
    }

    addMsg(serverity, detail, clear, data?) {
        if (clear) {
            this.clearMessages();
        };
        this.translateService.get(detail).subscribe((res: string) => {
            detail = res;
            if (data != null && data != undefined) {
                for (let i = 0; i < data.length; i++) {
                    detail = detail.replace("{" + i + "}", data[i]);
                }
            }
            let level: number = 0;
            if (serverity == 'success') {
                level = 0;
            } else if (serverity == 'info') {
                level = 1;
            } else if (serverity == 'warn') {
                level = 2;
            } else if (serverity == 'error') {
                level = 3;
            }
            this.tabView.toastComponent.addMessage(detail, level);
        });
    }

    /**
     * Method to set loading for a tab
     * @param loading 
     */
    setLoadingTab(loading: boolean) {
        if (this.tabView != null && this.tabView! != undefined) {
            this.tabView.tabViewBody.loading = loading;
        } else {
            this.loading = loading;
        }
    }

    /**
     * Method to get update z-index
     */
    getUpdatedZIndex() {
        this.zindex = CoreUtils.MODAL_Z_INDEX++;
        return this.zindex;
    }

    /**
     * Method to open modal
     */
    openModal(label, componentId: string, mapParams) {
        let modalId = "modal";
        this.setLoadingTab(false);
        if (this.tabView != null && this.tabView != undefined) {
            // First search index for modal in tabView
            let index = this.tabView.tabViewBody.tabViewModalsContent.length;
            let dynamicComponentLoader: DynamicLoaderComponent = this.tabView.tabViewComponent.dynamicComponentLoader;
            // Load modal to body tabView
            dynamicComponentLoader
                .getComponentFactory<any>(modalId)
                .subscribe(componentFactory => {
                    const refModal: any = this.tabView.tabViewBody.tabViewModalsContent.createComponent(componentFactory);
                    this.tabView.tabViewBody.tabViewModalsContent.element.nativeElement.appendChild(refModal.location.nativeElement);
                    refModal._component.label = label;
                    refModal._component.index = index;
                    this.tabView.tabViewBody.changeDetectorRef.markForCheck();
                    this.tabView.tabViewBody.changeDetectorRef.detectChanges();
                    // Load component inside body modal 
                    dynamicComponentLoader
                        .getComponentFactory<any>(componentId)
                        .subscribe(componentFactory => {
                            const ref: any = this.tabView.tabViewBody.tabViewModalsContent.createComponent(componentFactory);
                            refModal._component.modalViewBody.element.nativeElement.appendChild(ref.location.nativeElement);
                            refModal._component.baseComponent = ref._component;
                            ref._component.modalComponent = refModal._component;
                            ref._component.modal = true;
                            if (mapParams != null && mapParams != undefined) {
                                let keys: string[] = Array.from(mapParams.keys());
                                for (let key of keys) {
                                    if (key.trim() !== 'modalAction') {
                                        ref._component[key] = mapParams.get(key);
                                    }
                                }
                                if (mapParams.has('modalAction')) {
                                    let acctions = mapParams.get('modalAction')
                                    if (acctions != null && acctions != undefined) {
                                        for (let i = 0; i < acctions.length; i++) {
                                            ref._component[acctions[i]]();
                                        }
                                    }
                                }
                            }
                            refModal.changeDetectorRef.markForCheck();
                            refModal.changeDetectorRef.detectChanges();
                            this.setLoadingTab(false);
                            this.tabView.tabViewBody.changeDetectorRef.markForCheck();
                            this.tabView.tabViewBody.changeDetectorRef.detectChanges();
                        }, error => {
                            this.setLoadingTab(false);
                            console.warn(error);
                        });
                }, error => {
                    this.setLoadingTab(false);
                    console.warn(error);
                });
        }
    }
}