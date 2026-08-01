package org.carecampuspartner.screens.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.carecampuspartner.screens.volunteer.VolunteerRequests
import org.carecampuspartner.screens.volunteer.VolunteerRequestsScreen


object Home : Screen {
    @Composable
    override fun Content() {

        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            onReportClick = {  },
            onDonateClick = {  },
            onAiToolsClick = { },
            onAdoptClick = {  },
            onMoreClick = {  },
            onMyPetsClick = {  },
            onLostFoundClick = {  },
            onNearbyVetsClick = {  },
            onBannerClick = {  },
            onVolunteerClick = { navigator.push(VolunteerRequests) }

        )
    }

}