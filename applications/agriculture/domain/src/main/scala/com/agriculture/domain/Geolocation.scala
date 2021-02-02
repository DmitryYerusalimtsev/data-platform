package com.agriculture.domain

import java.time.LocalDateTime

case class Geolocation(
                        longitude: Double,
                        latitude: Double,
                        timestamp: LocalDateTime
                      )
