

export default function defaultPropertiesComponents(other) {
    var properties = {
        appcomponent: null,
        basecomponent: null,
        tabViewComponent: null,
        model: null,
        property: null,
        propertyId: null,
        serviceName: null,
        propertyFind: 'code',
        property2: 'description',
        mapProperties: {},
    };
    if (other != null && other != undefined) {
        Object.keys(other).forEach(function (key) {
            properties[key] = other[key];
        });
    }
    return properties;
} 