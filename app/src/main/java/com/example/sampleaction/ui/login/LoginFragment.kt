package com.example.sampleaction.ui.login

import android.os.Bundle
import android.view.View
import com.example.sampleaction.R
import com.example.sampleaction.baseclasses.BaseFragment
import com.example.sampleaction.databinding.FragmentLoginBinding
import com.example.sampleaction.utils.extentions.observeData
import org.koin.androidx.viewmodel.ext.android.viewModel

/*********************************************************
 * Class   :  LoginFragment
 * Author  :
 * Created :  05/08/21
 *******************************************************
 * Purpose :  LoginFragment class
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
class LoginFragment : BaseFragment<FragmentLoginBinding>(
    layoutId = R.layout.fragment_login
) {
    private val loginViewModel: LoginViewModel by viewModel()

    override fun onBindingCreated(binding: FragmentLoginBinding) {
        super.onBindingCreated(binding)
        binding.viewModel = loginViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel.run {
            observeData(messageEvent) { showMessageEvent(it) }
            observeData(navigation) { handleNavigation(it) }
            observeData(loginError) {
                showAlertDialog(
                    title = R.string.sorry,
                    message = getString(R.string.login_failed, it)
                )
            }
            observeData(loginResponse) {
                if(it) {
                    navigate(LoginFragmentDirections.showOrderListFragment())
                } else {
                    showAlertDialog(
                        title = R.string.sorry,
                        message = getString(R.string.login_failed, getString(R.string.unknown))
                    )
                }
            }
        }
    }
}