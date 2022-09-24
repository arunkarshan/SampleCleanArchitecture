package com.example.sampleaction.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.base.ResultWrapper
import com.example.domain.login.AuthenticateLoginUseCase
import com.example.sampleaction.R
import com.example.sampleaction.ui.login.LoginViewModel
import com.example.utils.test.InstantExecutorExtension
import com.example.utils.test.getOrAwaitValue
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.rules.TestRule
import java.lang.RuntimeException

/*********************************************************
 * Class   :  LoginViewModelTest
 * Author  :  Arun Nair
 * Created :  22/09/2022
 *
 * Purpose :
 *
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 **********************************************************/
@ExtendWith(InstantExecutorExtension::class)
internal class LoginViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private lateinit var loginUseCase: AuthenticateLoginUseCase

    private lateinit var loginViewModel: LoginViewModel

    private val email = "test@sample.com"
    private val password = "testpassword"

    private val loginSuccess = flowOf(ResultWrapper.Success(true))
    private val loginFailed = flowOf(ResultWrapper.Success(false))
    private val loginEmailError = flowOf(ResultWrapper.Error(RuntimeException("No user found!")))
    private val loginPasswordError = flowOf(ResultWrapper.Error(RuntimeException("Invalid credentials!")))

    @BeforeEach
    fun setUp(){
        loginUseCase = mockk()
        loginViewModel = LoginViewModel(loginUseCase)
        coEvery { loginUseCase.invoke(any()) } returnsMany listOf(
            loginEmailError, loginPasswordError, loginFailed, loginSuccess
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun authenticateLogin() = runBlockingTest {
        fun invalidEmailValidation() {
            loginViewModel.authenticateLogin()
            assertEquals(R.string.enter_valid_email, loginViewModel.emailError.getOrAwaitValue())
            assertEquals( "", loginViewModel.password.getOrAwaitValue())
            assertEquals(null, loginViewModel.passwordError.getOrAwaitValue())
            verify { loginUseCase wasNot Called }
        }

        fun invalidPasswordValidation() {
            loginViewModel.authenticateLogin()
            assertEquals(null, loginViewModel.emailError.getOrAwaitValue())
            assertEquals(R.string.password_should_be_minimum, loginViewModel.passwordError.getOrAwaitValue())
            verify { loginUseCase wasNot Called }
        }

        val invalidEmail1 = "testatsample.com"
        val invalidEmail2 = "@sample.com"
        val invalidPassword = "1234"
        val invalidEmailError = "No user found!"
        val invalidPasswordError = "Invalid credentials!"


        invalidEmailValidation()

        loginViewModel.onEmailChanged(invalidEmail1)
        invalidEmailValidation()

        loginViewModel.onEmailChanged(invalidEmail2)
        invalidEmailValidation()

        loginViewModel.onEmailChanged(email)
        invalidPasswordValidation()

        loginViewModel.onPasswordChanged(invalidPassword)
        invalidPasswordValidation()

        loginViewModel.onPasswordChanged(password)
        loginViewModel.authenticateLogin()
        assertEquals(null, loginViewModel.emailError.getOrAwaitValue())
        assertEquals(null, loginViewModel.passwordError.getOrAwaitValue())
        assertEquals(invalidEmailError, loginViewModel.loginError.getOrAwaitValue())

        //Usecase Return invalidEmail
        loginViewModel.authenticateLogin()
        assertEquals(invalidEmailError, loginViewModel.loginError.getOrAwaitValue())

        //Usecase Return invalidPassword
        loginViewModel.authenticateLogin()
        assertEquals(invalidPasswordError, loginViewModel.loginError.getOrAwaitValue())

        //Usecase Return loginFailed
        loginViewModel.authenticateLogin()
        assertEquals(false, loginViewModel.loginResponse.getOrAwaitValue())

        //Usecase Return loginSuccess
        loginViewModel.authenticateLogin()
        assertEquals(true , loginViewModel.loginResponse.getOrAwaitValue())
    }


    @Test
    fun testOnEmailChanged() = runBlocking {
        //When
        loginViewModel.onEmailChanged(email)

        //Then
        assertEquals(email, loginViewModel.email.getOrAwaitValue())
    }

    @Test
    fun onPasswordChanged() {
        //When
        loginViewModel.onEmailChanged(password)

        //Then
        assertEquals(password, loginViewModel.email.getOrAwaitValue())
    }
}