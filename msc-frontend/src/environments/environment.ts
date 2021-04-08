// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  msc_api:'/cidadao/v1',
  mimg_api:'/georreferenciamento/v1',
  stur_api:'/stur/v1',
  seguranca_api:'/seguranca/v1',
  tempo_validade:30,
  loader_time_show:500,
  loader_time_hiden:0
};


/*
* For easier debugging in development mode, you can import the following file
* to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
*
* This import should be commented out in production mode because it will have a negative impact
* on performance if an error is thrown.
*/
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
