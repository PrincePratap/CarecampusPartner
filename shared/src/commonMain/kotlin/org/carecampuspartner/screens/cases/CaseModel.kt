package org.carecampuspartner.screens.cases

import kotlinx.serialization.Serializable

@Serializable
data class RescueCase(
    val caseId: String,
    val citizenId: String,
    val citizenName: String,
    val phone: String,
    val animalType: String,
    val description: String,
    val condition: String,
    val priority: CasePriority,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val photoUrls: List<String>,
    val status: CaseStatus,
    val createdAt: Long,
    val updatedAt: Long,
    val distance: String,
    val isEmergency: Boolean,
    val estimatedArrivalTime: String? = null,
    val rejectionReason: String? = null,
    val injuryLevel: String = "Medium"
)

enum class CasePriority {
    HIGH, MEDIUM, LOW
}

enum class CaseStatus {
    PENDING,
    ACCEPTED,
    ASSIGNED,
    ON_THE_WAY,
    RESCUED,
    COMPLETED,
    REJECTED
}
