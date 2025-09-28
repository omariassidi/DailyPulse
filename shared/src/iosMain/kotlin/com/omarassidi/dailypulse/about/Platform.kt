package com.omarassidi.dailypulse.about

import platform.UIKit.UIDevice
import platform.UIKit.UIScreen
import kotlin.math.round

actual class Platform {
    actual val osName: String = "iOS"
    actual val osVersion: String = UIDevice.currentDevice.systemVersion
    actual val deviceModel: String = UIDevice.currentDevice.model
    actual val density: Int = round(UIScreen.mainScreen.scale).toInt()
}