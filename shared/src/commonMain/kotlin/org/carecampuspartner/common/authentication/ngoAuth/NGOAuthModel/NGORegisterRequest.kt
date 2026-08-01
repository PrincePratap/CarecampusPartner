package org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



@Serializable
data class NGORegisterResponse(
    val success: Boolean,
    val message: String,
    val data: NGORegisterData
)

@Serializable
data class NGORegisterData(
    @SerialName("ngo_id")
    val ngoId: String
)


@Serializable
data class NGORegisterRequest(
    @SerialName("full_name")
    val fullName: String,

    val email: String,

    val phone: String,

    val password: String,

    @SerialName("ngo_name")
    val ngoName: String
)