package com.example.sampleaction.baseclasses

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.example.sampleaction.models.events.Event
import com.example.sampleaction.models.events.MessageEvent
import com.example.sampleaction.models.navigation.NavigationCommand
import com.example.sampleaction.utils.extentions.showMessageEvent
import com.example.utils.alerts.showSnackbar

/*********************************************************
 * Class   :  BaseFragment
 * Author  :  Arun Nair
 * Created :  13/2/21
 *******************************************************
 * Purpose :  Handles base variables in common-ui
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/

abstract class BaseFragment<DataBindingType : ViewDataBinding>(
    private val layoutId: Int,
    private val menuId: Int? = null,
    private val isBackPressHandled: Boolean = false
) : Fragment(), LifecycleObserver {

    private var dialog: AlertDialog? = null
    internal var binding: DataBindingType? = null


    /***************************************
     * On Create View
     ***************************************/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil
            .inflate(inflater, layoutId, container, false) as DataBindingType
        binding?.let { onBindingCreated(it) }

        setHasOptionsMenu(menuId != null)

        activity?.lifecycle?.addObserver(this)

        return binding?.apply {
            lifecycleOwner = viewLifecycleOwner
        }?.root
    }

    /**
     * *************************************
     * OnOptionsMenu Create
     * **************************************
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isBackPressHandled) {
            activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        onBackPress()
                    }
                })
        }
    }

    /**
     * *************************************
     * OnOptionsMenu Create
     * **************************************
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
    /**
     * *************************************
     * OnOptionsMenu Create
     * **************************************
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menuId?.let {
            inflater.inflate(it, menu)
        }
    }

    /**
     * *************************************
     * OnOptionsMenu Create
     * **************************************
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPress()
        }
        return super.onOptionsItemSelected(item)
    }

    /***************************************
     * Show Exit Confirmation
     ***************************************/
    fun <T> showMessageEvent(event: MessageEvent<T>?) {
        when (event?.eventType) {
            Event.EventType.SHOW_SNACKBAR -> showSnackbar(
                event.message,
                event.duration
            )
            else -> activity?.showMessageEvent(event)
        }
    }

    /***************************************
     * Setting Observers
     ***************************************/
    fun handleNavigation(navigationCommand: NavigationCommand) {
        try {
            getNavController()?.run {
                when (navigationCommand) {
                    is NavigationCommand.To -> navigate(navigationCommand.directions, getExtras())
                    is NavigationCommand.DeepLink -> navigate(navigationCommand.uri)
                    is NavigationCommand.POP -> {
                        if (!navigateUp()) {
                            requireActivity().finish()
                        } else { /*Nothing to do */ }
                    }
                    is NavigationCommand.Back -> onBackPress()
                    is NavigationCommand.FinishActivity -> activity?.finish()
                    is NavigationCommand.ConfirmFinishActivity -> showConfirmFinishActivityDialog()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            requireActivity().finish()
        }
    }

    /***************************************
     * Function for overriding findNavController
     ***************************************/
    open fun getNavController(): NavController? = findNavController()


    /***************************************
     * Setting Observers
     ***************************************/
    open fun onBackPress() {
        if(!findNavController().navigateUp()){
            requireActivity().finish()
        }
    }

    /***************************************
     * [FragmentNavigatorExtras] mainly used
     * to enable Shared Element transition
     ***************************************/
    open fun getExtras(): FragmentNavigator.Extras = FragmentNavigatorExtras()

    /***************************************
     * Setting Observers
     ***************************************/
    open fun showConfirmFinishActivityDialog() {}
    open fun onBindingCreated(binding: DataBindingType) {}

    /***************************************
     * Setting Observers
     ***************************************/
    private fun hasPermission(permission: String): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(
            requireContext(),
            permission
        )
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

}