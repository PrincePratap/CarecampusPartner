package org.carecampuspartner.screens.authentication.util

import org.carecampuspartner.common.util.isValidEmail
import org.carecampuspartner.common.util.isValidPassword
import org.carecampuspartner.screens.authentication.ngoCreateAccount._registerUiState

fun validateRegister(): Boolean {
    val state = _registerUiState.value
    var isValid = true

    val fullNameError = when {
        state.fullName.isBlank() -> "Name required"
        state.fullName.length < 3 -> "Minimum 3 characters"
        else -> null
    }

    val emailError = when {
        state.email.isBlank() -> "Email required"
        !isValidEmail(state.email) -> "Email invalid"
        else -> null
    }

    val phoneError = when {
        state.phone.isBlank() -> "Phone required"
        state.phone.length != 10 -> "Phone invalid (10 digits)"
        else -> null
    }

    val passwordError = when {
        state.password.isBlank() -> "Password required"
        !isValidPassword(state.password) -> "Password too weak"
        else -> null
    }

    val confirmPasswordError = when {
        state.confirmPassword != state.password -> "Passwords do not match"
        else -> null
    }

    if (fullNameError != null || emailError != null || phoneError != null || passwordError != null || confirmPasswordError != null) {
        _registerUiState.value = state.copy(
            fullNameError = fullNameError,
            emailError = emailError,
            phoneError = phoneError,
            passwordError = passwordError,
            confirmPasswordError = confirmPasswordError
        )
        isValid = false
    }

    return isValid
}