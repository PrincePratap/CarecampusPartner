package org.carecampuspartner.screens.volunteer

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.carecampuspartner.screens.onboarding.EnableLocationScreen


object VolunteerProfile : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        VolunteerProfileScreen(

        )
    }
}