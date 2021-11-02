package com.example.demoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.demoapp.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private  val homeFragment = HomeFragment()
    private  val categoriesFragment = CategoriesFragment()
    private  val dashboardFragment  = DashboardFragment()
    private  val notificationFragment = NotificationFragment()
    private  val moreFragment = MoreFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navigarionbar = findViewById<BottomNavigationView>(R.id.bottom_navigationbar) as BottomNavigationView
        replaceFragment(homeFragment,"Home")
        navigarionbar.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> replaceFragment(homeFragment,"Home")
                R.id.menu_categories -> replaceFragment(categoriesFragment,"Categories")
                R.id.menu_dashboard -> replaceFragment(dashboardFragment,"Dashboard")
                R.id.menu_notification -> replaceFragment(notificationFragment,"Notifications")
                R.id.menu_more -> replaceFragment(moreFragment,"More")
            }
            true
        }
    }

    private  fun replaceFragment(fragment : Fragment,tittle : String ){
        if(fragment!=null){
            supportActionBar?.setTitle(tittle);
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout_id,fragment)
            transaction.commit()
        }
    }
}