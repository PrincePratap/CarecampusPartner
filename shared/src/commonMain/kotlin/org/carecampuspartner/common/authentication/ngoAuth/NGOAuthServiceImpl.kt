package org.carecampuspartner.common.authentication.ngoAuth

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.NGORegisterResponse
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpRequest
import org.carecampuspartner.common.authentication.ngoAuth.NGOAuthModel.VerifyOtpResponse


internal class NGOAuthServiceImpl(private val client: HttpClient) : NGOAuthService {

    override suspend fun register(request: NGORegisterRequest): NGORegisterResponse {
        return client.post("http://143.110.186.77:8000/ngo/register") {
            setBody(request)
        }.body()
    }

    override suspend fun verifyOtp(request: VerifyOtpRequest): VerifyOtpResponse {
        return client.post("http://143.110.186.77:8000/ngo/verify-otp") {
            setBody(request)
        }.body()
    }


}
