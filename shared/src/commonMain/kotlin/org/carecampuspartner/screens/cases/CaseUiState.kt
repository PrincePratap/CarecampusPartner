package org.carecampuspartner.screens.cases

import androidx.compose.runtime.Immutable

@Immutable
data class CasesUiState(
    val isLoading: Boolean = false,
    val cases: List<RescueCase> = emptyList(),
    val error: String? = null,
    val searchQuery: String = "",
    val selectedFilterStatus: CaseStatus? = null,
    val selectedAnimalType: String? = null,
    val selectedPriority: CasePriority? = null,
    val stats: CaseStats = CaseStats(),
    val isRefreshing: Boolean = false
)

data class CaseStats(
    val pendingCount: Int = 0,
    val acceptedCount: Int = 0,
    val completedCount: Int = 0,
    val emergencyCount: Int = 0
)

sealed class CasesEvent {
    data class OnSearchQueryChange(val query: String) : CasesEvent()
    data class OnFilterStatusChange(val status: CaseStatus?) : CasesEvent()
    data class OnAcceptCase(val case: RescueCase) : CasesEvent()
    data class OnRejectCase(val case: RescueCase, val reason: String, val notes: String) : CasesEvent()
    object OnRefresh : CasesEvent()
    data class OnViewDetails(val case: RescueCase) : CasesEvent()
}
