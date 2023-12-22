package com.example.projek_alsa.Public

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.projek_alsa.Admin.adminHomeActivity
import com.example.projek_alsa.R
import com.example.projek_alsa.sharedPreferences.prefData
import com.example.projek_alsa.sharedPreferences.sharedPreference
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.Tab

class loginRegisterActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager2: ViewPager2
    private lateinit var adapter: loginRegisterAdapter
    private lateinit var sharedPref: sharedPreference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_register)
        sharedPref = sharedPreference(this)
        tabLayout = findViewById(R.id.tl_navbar_logregis)
        viewPager2 = findViewById(R.id.vp_container_logregis)

        adapter = loginRegisterAdapter(supportFragmentManager, lifecycle)

        tabLayout.addTab(tabLayout.newTab().setText("Login"))
        tabLayout.addTab(tabLayout.newTab().setText("Register"))

        viewPager2.adapter = adapter

        tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: Tab?) {
                if (tab != null) {
                    viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: Tab?) {

            }

            override fun onTabReselected(tab: Tab?) {

            }

        })

        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tabLayout.selectTab(tabLayout.getTabAt(position))
            }
        })

    }

    override fun onStart() {
        super.onStart()
        if(sharedPref.getBoolean(prefData.PREF_IS_LOGIN) == true){
            startActivity(Intent(this@loginRegisterActivity, adminHomeActivity::class.java))
            finish()
        }
    }
}