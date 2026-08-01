package org.carecampuspartner.common.authentication.ngoAuth

import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterResponse
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpResponse
import org.carecampuspartner.common.data.remote.Result

interface NGOAuthRepository {
    suspend fun register(request: NGORegisterRequest): Result<NGORegisterResponse>
    suspend fun verifyOtp(request: VerifyOtpRequest): Result<VerifyOtpResponse>
}