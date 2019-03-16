import { DynamicLoaderManifestComponent } from "../dynamic/dynamic.loader.module";

// This array defines which "componentId" maps to which lazy-loaded module.
export const TAB_VIEW_MANIFEST: DynamicLoaderManifestComponent[] = [
    {
        componentId: 'tabviewheader',
        path: 'tabviewheader', // some globally-unique identifier, used internally by the router
        loadChildren: './tabviewheader.module#TabViewHeaderModule',
    },
    {
        componentId: 'tabviewbody',
        path: 'tabviewbody', // some globally-unique identifier, used internally by the router
        loadChildren: './tabviewbody.module#TabViewBodyModule',
    }
];