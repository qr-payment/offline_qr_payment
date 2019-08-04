package com.example.user_client_kotlin

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.example.user_client_kotlin.ui.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = MainTransactionFragment()
        supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
            .commit()

        //권한처리먼저?
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CAMERA),
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS)
            }
        }else {
            //권한을 이미 승인한 경우
        }

        bottomNav.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.action_first -> {
                    val fragment = MainTransactionFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_second -> {
                    val fragment = TransferFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_third -> {
                    val fragment = InvestmentFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_fourth -> {
                    val fragment = HistoryFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_fifth -> {
                    val fragment = EtcFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container_frame, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {

                } else {
                    //make custom dialog
                }
                return
            }
        }
    }
}



