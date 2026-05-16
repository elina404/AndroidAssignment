package com.example.assignment

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class FragmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val btn1 = findViewById<Button>(R.id.btnShowFragment1)
        val btn2 = findViewById<Button>(R.id.btnShowFragment2)

        // Chapter 6 Task 1 & 4: Load initial fragment and manage navigation
        if (savedInstanceState == null) {
            replaceFragment(FragmentOne())
        }

        btn1.setOnClickListener {
            replaceFragment(FragmentOne())
        }

        btn2.setOnClickListener {
            replaceFragment(FragmentTwo())
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}