package org.carecampuspartner.screens.cases

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

class CasesScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        var uiState by remember { mutableStateOf(CasesUiState(isLoading = false)) }
        var showAcceptDialog by remember { mutableStateOf<RescueCase?>(null) }
        var showRejectSheet by remember { mutableStateOf<RescueCase?>(null) }

        // Mock data for initial development
        LaunchedEffect(Unit) {
            uiState = uiState.copy(
                cases = listOf(
                    RescueCase(
                        caseId = "1001",
                        citizenId = "c1",
                        citizenName = "John Doe",
                        phone = "+1234567890",
                        animalType = "Dog",
                        description = "Injured dog found near the park. Seems to have a broken leg.",
                        condition = "Injured",
                        priority = CasePriority.HIGH,
                        latitude = 0.0,
                        longitude = 0.0,
                        address = "Central Park, New York",
                        photoUrls = emptyList(),
                        status = CaseStatus.PENDING,
                        createdAt = 0,
                        updatedAt = 0,
                        distance = "1.2 km",
                        isEmergency = true
                    ),
                    RescueCase(
                        caseId = "1002",
                        citizenId = "c2",
                        citizenName = "Jane Smith",
                        phone = "+0987654321",
                        animalType = "Cat",
                        description = "Cat stuck on a tree for 2 days.",
                        condition = "Trapped",
                        priority = CasePriority.MEDIUM,
                        latitude = 0.0,
                        longitude = 0.0,
                        address = "5th Avenue, New York",
                        photoUrls = emptyList(),
                        status = CaseStatus.ACCEPTED,
                        createdAt = 0,
                        updatedAt = 0,
                        distance = "3.5 km",
                        isEmergency = false,
                        estimatedArrivalTime = "15 mins"
                    )
                ),
                stats = CaseStats(
                    pendingCount = 5,
                    acceptedCount = 2,
                    completedCount = 12,
                    emergencyCount = 1
                )
            )
        }

        Scaffold(
            topBar = {
                CasesTopBar(
                    onBack = { navigator.pop() },
                    onNotify = {},
                    onSearch = {},
                    onFilter = {}
                )
            },
            containerColor = CBackground
        ) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SummaryCards(uiState.stats)
                CaseSearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = { uiState = uiState.copy(searchQuery = it) }
                )
                
                if (uiState.isLoading) {
                    LoadingState()
                } else if (uiState.cases.isEmpty()) {
                    EmptyState(onRefresh = {})
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(bottom = 80.dp)
                    ) {
                        items(uiState.cases) { case ->
                            CaseCard(
                                case = case,
                                onAccept = { showAcceptDialog = case },
                                onReject = { showRejectSheet = case },
                                onViewDetails = {},
                                onNavigate = {},
                                onCall = {}
                            )
                        }
                    }
                }
            }
        }

        showAcceptDialog?.let { case ->
            AcceptDialog(
                onConfirm = {
                    uiState = uiState.copy(
                        cases = uiState.cases.map {
                            if (it.caseId == case.caseId) it.copy(status = CaseStatus.ACCEPTED, estimatedArrivalTime = "20 mins") else it
                        }
                    )
                    showAcceptDialog = null
                },
                onDismiss = { showAcceptDialog = null }
            )
        }

        showRejectSheet?.let { case ->
            RejectBottomSheet(
                onReject = { reason, notes ->
                    uiState = uiState.copy(
                        cases = uiState.cases.map {
                            if (it.caseId == case.caseId) it.copy(status = CaseStatus.REJECTED, rejectionReason = reason) else it
                        }
                    )
                    showRejectSheet = null
                },
                onDismiss = { showRejectSheet = null }
            )
        }
    }
}
