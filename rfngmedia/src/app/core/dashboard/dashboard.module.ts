import { DashboardComponent } from "./dashboard.component";
import { Routes } from "@angular/router";
import { CommonModule } from "@angular/common";
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from "@angular/core";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { DynamicComponentLoaderModule, DynamicLoaderManifestComponent } from "../../../../rfng/src/public_api";
import { MenuModule } from "../menu/menu.module";
import { I18nTranslateModule } from "../../../../rfng/src/core/i18n/i18ntranslate.module";
import { HeaderModule } from "../header/header.module";


// This array defines which "componentId" maps to which lazy-loaded module.
let mainefestRoutes: DynamicLoaderManifestComponent[] = [
  {
    componentId: 'tabview',
    path: 'tabview', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/tabview/tabview.module#TabViewModule',
  },
  {
    componentId: 'tabviewheader',
    path: 'tabviewheader', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/tabview/tabviewheader.module#TabViewHeaderModule',
  },
  {
    componentId: 'tabviewbody',
    path: 'tabviewbody', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/tabview/tabviewbody.module#TabViewBodyModule',
  },
  {
    componentId: 'confirmation',
    path: 'confirmation', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/modal/confirmation/confirmation.module#ConfirmationModule',
  },
  {
    componentId: 'modal',
    path: 'modal', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/modal/modal.module#ModalModule',
  },
  {
    componentId: 'configBrowserModal',
    path: 'configBrowserModal', // some globally-unique identifier, used internally by the router
    loadChildren: '../../../../rfng/src/core/components/modal/configbrowsermodal/configbrowsermodal.module#ConfigBrowserModalModule',
  },
  // Routes animes 
  {
    componentId: 'animes_categorias',
    path: 'animes_categorias', // some globally-unique identifier, used internally by the router
    loadChildren: '../../animes/categorias/browser.categorias.animes.module#BrowserCategoriasAnimesModule',
  },
  {
    componentId: 'animes_categorias_red',
    path: 'animes_categorias_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../animes/categorias/red.categorias.animes.module#RedCategoriasAnimesModule',
  },
  {
    componentId: 'animes_series',
    path: 'animes_series', // some globally-unique identifier, used internally by the router
    loadChildren: '../../animes/series/browser.series.animes.module#BrowserSeriesAnimesModule',
  },
  {
    componentId: 'animes_series_red',
    path: 'animes_series_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../animes/series/red.series.animes.module#RedSeriesAnimesModule',
  },
  // Routes almacenes
  {
    componentId: 'almacenes_almacen',
    path: 'almacenes_almacen', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/almacenes/browser.almacenes.almacen.module#BrowserAlmacenesAlmacenModule',
  },
  {
    componentId: 'almacenes_almacen_red',
    path: 'almacenes_almacen_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/almacenes/red.almacenes.almacen.module#RedAlmacenesAlmacenModule',
  },
  {
    componentId: 'almacenes_categorias',
    path: 'almacenes_categorias', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/categorias/browser.categorias.almacen.module#BrowserCategoriasAlmacenModule',
  },
  {
    componentId: 'almacenes_categorias_red',
    path: 'almacenes_categorias_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/categorias/red.categorias.almacen.module#RedCategoriasAlmacenModule',
  },
  {
    componentId: 'almacenes_articulos',
    path: 'almacenes_articulos', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/articulos/browser.articulos.almacen.module#BrowserArticulosAlmacenModule',
  },
  {
    componentId: 'almacenes_articulos_red',
    path: 'almacenes_articulos_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/articulos/red.articulos.almacen.module#RedArticulosAlmacenModule',
  },
  {
    componentId: 'almacenes_despensas',
    path: 'almacenes_despensas', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/despensas/browser.despensas.almacen.module#BrowserDespensasAlmacenModule',
  },
  {
    componentId: 'almacenes_despensas_red',
    path: 'almacenes_despensas_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../almacen/despensas/red.despensas.almacen.module#RedDespensasAlmacenModule',
  },
  // Routes gym
  {
    componentId: 'gym_categorias',
    path: 'gym_categorias', // some globally-unique identifier, used internally by the router
    loadChildren: '../../gym/categorias/browser.categorias.gym.module#BrowserCategoriasGymModule',
  },
  {
    componentId: 'gym_categorias_red',
    path: 'gym_categorias_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../gym/categorias/red.categorias.gym.module#RedCategoriasGymModule',
  },
  {
    componentId: 'gym_ejercicios',
    path: 'gym_ejercicios', // some globally-unique identifier, used internally by the router
    loadChildren: '../../gym/ejercicios/browser.ejercicios.gym.module#BrowserEjerciciosGymModule',
  },
  {
    componentId: 'gym_ejercicios_red',
    path: 'gym_ejercicios_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../gym/ejercicios/red.ejercicios.gym.module#RedEjerciciosGymModule',
  },
  // Routes games
  {
    componentId: 'games_categorias',
    path: 'game_categorias', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/categorias/browser.categorias.games.module#BrowserCategoriasGamesModule',
  },
  {
    componentId: 'games_categorias_red',
    path: 'games_categorias_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/categorias/red.categorias.games.module#RedCategoriasGamesModule',
  },
  {
    componentId: 'games_plataformas',
    path: 'game_plataformas', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/plataformas/browser.plataformas.games.module#BrowserPlataformasGamesModule',
  },
  {
    componentId: 'games_plataformas_red',
    path: 'games_plataformas_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/plataformas/red.plataformas.games.module#RedPlataformasGamesModule',
  },
  {
    componentId: 'games_juegos',
    path: 'game_juegos', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/juegos/browser.juegos.games.module#BrowserJuegosGamesModule',
  },
  {
    componentId: 'games_juegos_red',
    path: 'games_juegos_red', // some globally-unique identifier, used internally by the router
    loadChildren: '../../games/juegos/red.juegos.games.module#RedJuegosGamesModule',
  },
];

//RouterModule.forRoot(routesModule)
@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MenuModule,
    HeaderModule,
    I18nTranslateModule,
    DynamicComponentLoaderModule.forChild(DashboardComponent),
    DynamicComponentLoaderModule.forRoot(mainefestRoutes)
  ],
  declarations: [DashboardComponent],
  exports: [DashboardComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DashboardModule {

}