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

            let dataPaper = rfrt.loadConfigDataPaper(options);

            // Set background color
            container.style.backgroundColor = options.backgroundColor;

            // Create div for tools 
            let containerTools = document.createElement("DIV");
            containerTools.classList.add("ContainerToolsDiv");

            container.appendChild(containerTools);

            // Load tools data
            rfrt.loadToolsDataPaper(containerTools , dataPaper);


            // Create div for paper 
            let containerPaperDiv = document.createElement("DIV");
            containerPaperDiv.classList.add("ContainerPaperDiv");

            container.appendChild(containerPaperDiv);

            // Create paper 
            let containerPaper = document.createElement("DIV");
            containerPaper.classList.add("ContainerPaper");

            // Load data of paper 
            containerPaper.style.width = rfrt.cmTopx(rfrt.mmTocm(dataPaper.paperWidth)) + "px";

            // This is only for time to build report
            containerPaper.style.height = rfrt.cmTopx(rfrt.mmTocm(dataPaper.paperHeight)) + "px";

            containerPaperDiv.appendChild(containerPaper);

        } else {
            throw new Error("Id container report not defined");
        }
    }

    /**
     * Method to load data for tools
     */
    rfrt.loadToolsDataPaper = function (containerTools, options) {

    }

    /**
     * Method to load config for paper
     */
    rfrt.loadConfigDataPaper = function (options) {

        let jsonConfig = options;

        let dataPaper = {
            // Width of page
            "paperWidth": jsonConfig.paperWidth,
            // heigth of page
            "paperHeight": jsonConfig.paperHeight,
            // Title
            "title": jsonConfig.title
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
            "paperJson": null,
            // Title
            "title": "rfreports"
        };

        if (options == null || options == undefined) {
            options = {};
        }

        // Add options in default options
        Object.keys(options).forEach(function (key) {
            defaultOptions[key] = options[key];
        });

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
