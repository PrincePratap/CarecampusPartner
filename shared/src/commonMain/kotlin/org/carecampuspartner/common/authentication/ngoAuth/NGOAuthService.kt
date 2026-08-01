package org.carecampuspartner.common.authentication.ngoAuth

import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterResponse
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpResponse


internal interface NGOAuthService {
    suspend fun register(request: NGORegisterRequest): NGORegisterResponse
    suspend fun verifyOtp(request: VerifyOtpRequest): VerifyOtpResponse
}