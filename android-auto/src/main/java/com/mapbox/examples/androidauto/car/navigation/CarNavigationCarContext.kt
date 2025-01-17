package com.mapbox.examples.androidauto.car.navigation

import com.mapbox.androidauto.MapboxAndroidAuto
import com.mapbox.navigation.ui.maneuver.api.MapboxManeuverApi
import com.mapbox.examples.androidauto.car.MainCarContext
import com.mapbox.androidauto.car.navigation.voice.CarNavigationVoice

class CarNavigationCarContext(
    val mainCarContext: MainCarContext
) {
    /** MapCarContext **/
    val carContext = mainCarContext.carContext
    val mapboxCarMap = mainCarContext.mapboxCarMap
    val mapboxNavigation = mainCarContext.mapboxNavigation
    val distanceFormatter = mainCarContext.distanceFormatter

    /** NavigationCarContext **/
    val carDistanceFormatter = CarDistanceFormatter(
        mapboxNavigation.navigationOptions.distanceFormatterOptions.unitType
    )
    val carIconFactory = CarManeuverIconFactory(carContext)
    val maneuverMapper = CarManeuverMapper(carIconFactory)
    val navigationInfoMapper = CarNavigationInfoMapper(
        maneuverMapper,
        carDistanceFormatter
    )
    val maneuverApi: MapboxManeuverApi by lazy {
        MapboxManeuverApi(distanceFormatter)
    }
    val carNavigationVoice = CarNavigationVoice(
        mapboxNavigation,
        MapboxAndroidAuto.options.directionsLanguage
    )
}
