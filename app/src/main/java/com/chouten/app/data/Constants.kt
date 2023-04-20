package com.chouten.app.data

import android.os.Build
import android.os.Environment
import java.io.File
import com.chouten.app.ui.BottomNavItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import com.chouten.app.App
import com.chouten.app.PrimaryDataLayer
import com.chouten.app.R
import com.chouten.app.data.ChoutenSetting

object AppPaths {
    val baseDir =
        Environment.getExternalStoragePublicDirectory("${Environment.DIRECTORY_DOCUMENTS}/Chouten/")
    val _toCreate = listOf("Modules", "Themes")
    val addedDirs = mutableMapOf<String, File>()
}

object Preferences {
    const val SelectedModule = "SelectedModule"
    object Settings {
        val dynamicColor = ChoutenSetting(
            R.string.dynamic_colour_toggle__title,
            R.string.dynamic_colour_toggle__desc,
            preference = Pair("dynamicColor", Boolean),
            constraints = { Build.VERSION.SDK_INT > 31 }  // Disable if not Android 12 <=
        )
        {
            PrimaryDataLayer.enqueueSnackbar(
                SnackbarVisualsWithError(
                    "Restart for Changes to take Effect",
                    false,
                    customButton = SnackbarAction(
                        "Restart"
                    ) {
                        App.restart()
                    }
                )
            )
        }
    }
}

object NavigationItems {
    val HomePage = BottomNavItem(
        name = R.string.navbar_home,
        route = "home",
        activeIcon = Icons.Filled.Home,
        inactiveIcon = Icons.Outlined.Home,
    )
    val SettingsPage = BottomNavItem(
        name = R.string.navbar_settings,
        route = "settings",
        activeIcon = Icons.Filled.Settings,
        inactiveIcon = Icons.Outlined.Settings
    )
}