package com.kumbarakala.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kumbarakala.app.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AuthUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isAuthenticated: Boolean = false
)

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun updateEmail(value: String) = _uiState.update { it.copy(email = value) }

    fun updatePassword(value: String) = _uiState.update { it.copy(password = value) }

    fun login() = authenticate(register = false)

    fun register() = authenticate(register = true)

    private fun authenticate(register: Boolean) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true, error = null) }
        val state = _uiState.value
        val result = if (register) {
            repository.register(state.email, state.password)
        } else {
            repository.login(state.email, state.password)
        }
        _uiState.update {
            it.copy(
                isLoading = false,
                isAuthenticated = result.isSuccess,
                error = result.exceptionOrNull()?.message
            )
        }
    }

    fun continueAsDemo() {
        _uiState.update { it.copy(isAuthenticated = true) }
    }
}
