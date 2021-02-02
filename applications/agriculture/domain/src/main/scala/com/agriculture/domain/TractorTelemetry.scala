package com.agriculture.domain

case class TractorTelemetry(
                             id: Long,
                             location: Geolocation,
                             fuel: Fuel
                           )