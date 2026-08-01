package org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VerifyOtpRequest(
    @SerialName("ngo_id")
    val ngoId: String,
    val otp: String
)

@Serializable
data class VerifyOtpResponse(
    val success: Boolean,
    val message: String,
    val data: VerifyOtpData? = null
)

@Serializable
data class VerifyOtpData(
    @SerialName("ngo_id")
    val ngoId: String,
    @SerialName("ngo_name")
    val ngoName: String,
    @SerialName("owner_name")
    val ownerName: String,
    @SerialName("owner_email")
    val ownerEmail: String,
    @SerialName("owner_phone")
    val ownerPhone: String,
    @SerialName("total_volunteers")
    val totalVolunteers: Int,
    @SerialName("is_verified")
    val isVerified: Boolean,
    @SerialName("is_active")
    val isActive: Boolean,
    @SerialName("access_token")
    val accessToken: String
)
