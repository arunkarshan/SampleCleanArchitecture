package com.example.sampleaction.baseclasses

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/*********************************************************
 * Class   :  BaseActivity
 * Author  :  Arun Nair
 * Created :  13/2/21
 *******************************************************
 * Purpose :  Base Activity class
 *******************************************************
 * Rework Details:
 * 1) {Author} :  {Date} : {Details}
 *********************************************************/
@SuppressLint("Registered")
abstract class BaseActivity<out DataBindingType : ViewDataBinding>(
    protected open val layoutId: Int,
    protected open val menuId: Int? = null
) : AppCompatActivity() {

    /***************************************
     * Declarations
     ***************************************/
    private val binding by lazy {
        DataBindingUtil.setContentView(
            this,
            layoutId
        ) as DataBindingType
    }

    /***************************************
     * OnCreate
     ***************************************/
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        onCreate(savedInstanceState)
    }

    /***************************************
     * OnCreate
     ***************************************/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }

    /**
     * *************************************
     * OnOptionsMenu Item Selected Method
     * **************************************
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuId?.let { menuInflater.inflate(it, menu) }
        return true
    }

    /**
     * *************************************
     * OnOptionsMenu Item Selected Method
     * **************************************
     */

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

}
