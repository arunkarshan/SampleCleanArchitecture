package com.example.sampleaction.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.login.AuthenticateLoginUseCase
import com.example.sampleaction.R
import com.example.sampleaction.baseclasses.BaseViewModel
import com.example.sampleaction.models.events.SingleLiveEvent
import com.example.sampleaction.utils.extentions.collectWrapper
import com.example.sampleaction.utils.extentions.isNotValid
import com.example.sampleaction.utils.extentions.isValidEmailAddress
import org.koin.android.annotation.KoinViewModel


/*********************************************************
 * Class   :  LoginViewModel
 * Author  :  Arun Nair
 * Created :  15/09/2022
 *******************************************************
 * Purpose :
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@KoinViewModel
class LoginViewModel(
    private val authenticateLoginUseCase: AuthenticateLoginUseCase
) : BaseViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _loginResponse = MutableLiveData<Boolean>()
    val loginResponse: LiveData<Boolean> = _loginResponse

    val loginError = SingleLiveEvent<String?>()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _emailError = MutableLiveData<Int?>()
    val emailError: LiveData<Int?> = _emailError

    private val _passwordError = MutableLiveData<Int?>()
    val passwordError: LiveData<Int?> = _passwordError


    /***************************************
     * Setting Observers
     ***************************************/
    fun authenticateLogin() {
        if (validateCredentials()) {
            authenticateLoginUseCase.collectWrapper(
                scope = viewModelScope,
                param = AuthenticateLoginUseCase.Params(
                    email = _email.value ?: "",
                    password = _password.value ?: ""
                ),
                data = _loginResponse,
                error = loginError,
                loading = _isLoading
            )
        }
    }

    /***************************************
     * Setting Observers
     ***************************************/
    private fun validateCredentials(): Boolean {
        return if (!_email.value.isValidEmailAddress()) {
            _emailError.value = R.string.enter_valid_email
            _password.value = ""
            _passwordError.value = null
            false
        } else if (_password.value.isNotValid(minimumCharacters = 6)) {
            _emailError.value = null
            _passwordError.value = R.string.password_should_be_minimum
            false
        } else {
            _emailError.value = null
            _passwordError.value = null
            true
        }
    }

    /***************************************
     * Setting Observers
     ***************************************/
    fun onEmailChanged(email: CharSequence) {
        _email.takeIf { it.value != email.toString() }?.value = email.toString()
    }

    /***************************************
     * Setting Observers
     ***************************************/
    fun onPasswordChanged(pass: CharSequence) {
        _password.takeIf { it.value != pass.toString() }?.value = pass.toString()
    }
}