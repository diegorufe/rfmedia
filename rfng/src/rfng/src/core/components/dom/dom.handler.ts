import { Injectable } from "@angular/core";

@Injectable()
export class DomHandler{

    public static zindex: number = 1000;

    /**
     * Method to fade in style from a component
     * @param element 
     * @param duration 
     */
    public fadeIn(element, duration: number): void {
        element.style.opacity = 0;

        let last = +new Date();
        let opacity = 0;
        let tick = function () {
            opacity = +element.style.opacity.replace(",", ".") + (new Date().getTime() - last) / duration;
            element.style.opacity = opacity;
            last = +new Date();

            if (+opacity < 1) {
                (window.requestAnimationFrame && requestAnimationFrame(tick)) || setTimeout(tick, 16);
            }
        };

        tick();
    }

    /**
     * MEthod to fade out style from a component
     * @param element 
     * @param ms 
     */
    public fadeOut(element, ms) {
        var opacity = 1,
            interval = 50,
            duration = ms,
            gap = interval / duration;

        let fading = setInterval(() => {
            opacity = opacity - gap;

            if (opacity <= 0) {
                opacity = 0;
                clearInterval(fading);
            }
            
            element.style.opacity = opacity;
        }, interval);
    }
}