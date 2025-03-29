package com.example.jjurisai2

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.jjurisai2.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val toggle = ActionBarDrawerToggle(this,binding.drawerLayout, binding.toolbar, R.string.nav_open, R.string.nav_close)
        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        binding.navigationDrawer.setNavigationItemSelectedListener(this)

        fragmentManager = supportFragmentManager

        // ✅ Load HomeFragment by default when the app starts
        if (savedInstanceState == null) {
            openFragment(HomeFragment())
        }
    }

    @Deprecated("This method has been deprecated in favor of using the\n      {@link OnBackPressedDispatcher} via {@link #getOnBackPressedDispatcher()}.\n      The OnBackPressedDispatcher controls how back button events are dispatched\n      to one or more {@link OnBackPressedCallback} objects.")
    override fun onBackPressed(){
        if(binding.drawerLayout.isDrawerOpen(GravityCompat.START)){
            binding.drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressedDispatcher.onBackPressed()
        }
    }
    private fun openFragment(fragment: Fragment){
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()

        when (fragment){
            is ProfileFragment -> setTitle("Profile")
            is HomeFragment -> setTitle("JURIS AI")
            is Settings -> setTitle("Settings")
            is History -> setTitle("History")
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> openFragment(HomeFragment())
            R.id.nav_profile -> openFragment(ProfileFragment())
            R.id.nav_settings -> openFragment(Settings())
            R.id.nav_share -> Toast.makeText(this, "Share the app",Toast.LENGTH_SHORT).show()
            R.id.nav_logout -> {
                logoutUser()
                return true
            }
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun logoutUser() {
        val intent = Intent(this, LoginPage::class.java)
        startActivity(intent)  // ✅ Now it will navigate to LoginPage
        finish()  // ✅ Finish MainActivity to prevent going back on pressing back
    }
}