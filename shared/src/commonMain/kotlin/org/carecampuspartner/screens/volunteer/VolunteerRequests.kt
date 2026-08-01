package org.carecampuspartner.screens.volunteer

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

object VolunteerRequests : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        VolunteerRequestsScreen(
            onBack = { navigator.pop() },
            onVolunteerClick = { id ->
                navigator.push(VolunteerProfile)
            }
        )
    }
}
