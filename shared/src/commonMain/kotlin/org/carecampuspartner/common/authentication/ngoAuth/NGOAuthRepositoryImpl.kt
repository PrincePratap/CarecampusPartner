package org.carecampuspartner.common.authentication.ngoAuth

import kotlinx.coroutines.withContext
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterResponse
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpResponse
import org.carecampuspartner.common.data.remote.Result
import org.carecampuspartner.common.util.DispatcherProvider

internal class NGOAuthRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val authService: NGOAuthService
)  : NGOAuthRepository {

    override suspend fun register(request: NGORegisterRequest): Result<NGORegisterResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.register(request)
                if (response.success) {
                    Result.Success(response)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }

    override suspend fun verifyOtp(request: VerifyOtpRequest): Result<VerifyOtpResponse> {
        return withContext(dispatcher.io) {
            try {
                val response = authService.verifyOtp(request)
                if (response.success) {
                    Result.Success(response)
                } else {
                    Result.Error(response.message)
                }
            } catch (e: Exception) {
                Result.Error(e.message ?: "Unknown error")
            }
        }
    }


}