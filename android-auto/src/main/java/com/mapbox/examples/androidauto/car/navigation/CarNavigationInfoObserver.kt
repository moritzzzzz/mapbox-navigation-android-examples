package com.mapbox.examples.androidauto.car.navigation

import androidx.car.app.navigation.model.NavigationTemplate
import com.mapbox.androidauto.logAndroidAuto
import com.mapbox.bindgen.Expected
import com.mapbox.navigation.base.trip.model.RouteProgress
import com.mapbox.navigation.core.trip.session.RouteProgressObserver
import com.mapbox.navigation.ui.maneuver.model.Maneuver
import com.mapbox.navigation.ui.maneuver.model.ManeuverError

/**
 * Observe MapboxNavigation properties that create NavigationInfo.
 *
 * Attach the [start] [stop] functions to start observing navigation info.
 */
class CarNavigationInfoObserver(
    private val carNavigationCarContext: CarNavigationCarContext
) {
    private var onNavigationInfoChanged: (() -> Unit)? = null
    var navigationInfo: NavigationTemplate.NavigationInfo? = null
        private set(value) {
            if (field != value) {
                logAndroidAuto("CarNavigationInfoObserver navigationInfo changed")
                field = value
                onNavigationInfoChanged?.invoke()
            }
        }

    private var expectedManeuvers: Expected<ManeuverError, List<Maneuver>>? = null
    private var routeProgress: RouteProgress? = null

    private val routeProgressObserver = RouteProgressObserver { routeProgress ->
        this.routeProgress = routeProgress
        expectedManeuvers = carNavigationCarContext.maneuverApi.getManeuvers(routeProgress)
        updateNavigationInfo()
    }

    private fun updateNavigationInfo() {
        this.navigationInfo = carNavigationCarContext.navigationInfoMapper
            .from(expectedManeuvers, routeProgress)
    }

    fun start(onNavigationInfoChanged: () -> Unit) {
        this.onNavigationInfoChanged = onNavigationInfoChanged
        logAndroidAuto("CarRouteProgressObserver onStart")
        val mapboxNavigation = carNavigationCarContext.mapboxNavigation
        mapboxNavigation.registerRouteProgressObserver(routeProgressObserver)
    }

    fun stop() {
        logAndroidAuto("CarRouteProgressObserver onStop")
        onNavigationInfoChanged = null
        val mapboxNavigation = carNavigationCarContext.mapboxNavigation
        mapboxNavigation.unregisterRouteProgressObserver(routeProgressObserver)
    }
}
