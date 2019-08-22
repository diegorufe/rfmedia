window.RFReport = window.RFReport || {};

/**
 * 
 * @namespace RFReport
 */
(function (rfrt) {

    /**
     * 1cm is 37.795276px
     */
    rfrt.CM_PIXEL_UNIT = 37.795276;

    /**
     * 1px is 0.02645833cm
     */
    rfrt.PIXEL_CM_UNIT = 0.02645833;

    /**
     * Function to ini report 
     * @param id is the id for the container of the report,
     * @param options is a map for options to include in report tool 
     */
    rfrt.init = function (id, options) {
        let container = null;
        if (id != null && id != undefined) {
            container = document.getElementById(id);
        }

        options = rfrt.normalizeOptions(options);

        if (container != null && container != undefined) {
            container.classList.add("ContainerReportApp");
            // Set background color
            container.style.backgroundColor = options.backgroundColor;
            // Create paper 
            let containerPaper = document.createElement("DIV");
            containerPaper.classList.add("ContainerPaper");

            let dataPaper = rfrt.loadConfigDataPapter(options);

            // Load data of paper 
            containerPaper.style.width = rfrt.cmTopx(rfrt.mmTocm(dataPaper.paperWidth)) + "px";
            containerPaper.style.height = rfrt.cmTopx(rfrt.mmTocm(dataPaper.paperHeight)) + "px";

            container.appendChild(containerPaper);

        } else {
            throw new Error("Id container report not defined");
        }
    }

    /**
     * Method to load config for paper
     */
    rfrt.loadConfigDataPapter = function (options) {

        let jsonConfig = options;

        let dataPaper = {
            // Width of page
            "paperWidth": jsonConfig.paperWidth,
            // heigth of page
            "paperHeight": jsonConfig.paperHeight
        }

        return dataPaper;
    }

    /**
     * Method to normalizce and initialice options
     */
    rfrt.normalizeOptions = function normalizeOptions(options) {

        let defaultOptions = {
            // Container
            // Background color container
            "backgroundColor": "#808080",
            // Paper
            // Width of page in mm
            "paperWidth": 210,
            // heigth of page in mm
            "paperHeight": 297,
            // Paper json file for load configuration
            "paperJson": null
        };

        if (options == null || options == undefined) {
            options = {};
        }

        return defaultOptions;
    }

    /**
     * Method to conver milimeters to centimeters 
     */
    rfrt.mmTocm = function (number) {
        return number / 10;
    }

    /**
     * Method to conver centimeters to milimeters
     */
    rfrt.cmTomm = function (number) {
        return number * 10;
    }

    /**
     * Method to convert centirmeters to pixeles
     */
    rfrt.cmTopx = function (number) {
        return number / rfrt.PIXEL_CM_UNIT;
    }

}(RFReport));
