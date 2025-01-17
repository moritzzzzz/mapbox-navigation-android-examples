package com.mapbox.examples.androidauto.car.search

import com.mapbox.examples.androidauto.car.MainCarContext
import com.mapbox.examples.androidauto.car.preview.CarRouteRequest
import com.mapbox.search.MapboxSearchSdk

/**
 * Contains the dependencies for the search feature.
 */
class SearchCarContext(
    val mainCarContext: MainCarContext
) {
    /** MainCarContext **/
    val carContext = mainCarContext.carContext
    val distanceFormatter = mainCarContext.distanceFormatter

    /** SearchCarContext **/
    val carSearchEngine = CarSearchEngine(
        MapboxSearchSdk.createSearchEngine(),
        mainCarContext.navigationLocationProvider
    )
    val carRouteRequest = CarRouteRequest(mainCarContext)
}
