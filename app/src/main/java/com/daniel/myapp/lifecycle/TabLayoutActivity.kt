package com.daniel.myapp.lifecycle

import android.os.Bundle
import android.widget.TableLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.daniel.myapp.R
import com.daniel.myapp.test.tab_view.CobaTabAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class TabLayoutActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var vPager : ViewPager2
    private lateinit var tabAdapter : TabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_tab_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        vPager = findViewById(R.id.viewPager2)
        tabLayout = findViewById(R.id.tabLayout)

        tabAdapter = TabAdapter(this)
        vPager.adapter = tabAdapter

        TabLayoutMediator(tabLayout, vPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Home"
                1 -> "Profile"
                else -> "Home"
            }
        }.attach()

    }
}