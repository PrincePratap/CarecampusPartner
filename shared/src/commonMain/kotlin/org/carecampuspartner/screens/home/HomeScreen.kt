package org.carecampuspartner.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState

import androidx.compose.foundation.verticalScroll

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.carecampuspartner.screens.common.HomeItems.AnimalCategoriesSection
import org.carecampuspartner.screens.common.HomeItems.AskParoAIBanner
import org.carecampuspartner.screens.common.HomeItems.EmergencySOSBanner
import org.carecampuspartner.screens.common.HomeItems.HeaderSection
import org.carecampuspartner.screens.common.HomeItems.LatestRescueCasesSection
import org.carecampuspartner.screens.common.HomeItems.QuickActionsSection
import org.carecampuspartner.screens.common.HomeItems.SearchBar
import org.carecampuspartner.screens.common.HomeItems.SuccessStoriesSection
import org.carecampuspartner.screens.common.HomeItems.UpcomingRemindersSection
import org.carecampuspartner.screens.common.HomeItems.VolunteerRequestsSection
import org.carecampuspartner.theming.BackgroundLight


@Composable
fun HomeScreen(
    onReportClick: () -> Unit = {},
    onAdoptClick: () -> Unit = {},
    onDonateClick: () -> Unit = {},
    onMyPetsClick: () -> Unit = {},
    onAiToolsClick: () -> Unit = {},
    onLostFoundClick: () -> Unit = {},
    onNearbyVetsClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onBannerClick: () -> Unit = {},
    onVolunteerClick: () -> Unit = {}
) {
    var selectedCategory by remember { mutableStateOf("Dogs") }

    Scaffold(
        containerColor = BackgroundLight,
        bottomBar = {}
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp)
        ) {
            Spacer(modifier = Modifier.height(24.dp))
            HeaderSection()
            
            Spacer(modifier = Modifier.height(24.dp))
            SearchBar()
            
            Spacer(modifier = Modifier.height(24.dp))
            EmergencySOSBanner()
            
            Spacer(modifier = Modifier.height(32.dp))
            QuickActionsSection(
                onReportClick = { onReportClick() },
                onAdoptClick = { onAdoptClick() },
                onDonateClick = { onDonateClick() },
                onMyPetsClick = { onMyPetsClick() },
                onAiToolsClick = { onAiToolsClick() },
                onLostFoundClick = { onLostFoundClick() },
                onNearbyVetsClick = { onNearbyVetsClick() },
                onMoreClick = { onMoreClick() }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            AskParoAIBanner(
                onBannerClick = { onBannerClick() }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            VolunteerRequestsSection(
                onVolunteerClick = { volunteer ->
                   onVolunteerClick()
                },
                onSeeAllClick = {

                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            AnimalCategoriesSection(
                selectedCategory = selectedCategory,
                onCategoryClick = { category ->
                    selectedCategory = category
                }
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            LatestRescueCasesSection()
            
            Spacer(modifier = Modifier.height(32.dp))
            SuccessStoriesSection()

            Spacer(modifier = Modifier.height(100.dp)) // Extra space for bottom bar
        }
    }
}


















