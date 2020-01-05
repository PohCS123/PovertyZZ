package com.example.poverty.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.poverty.DonateFragment
import com.example.poverty.homepage.HomeFragment
import com.example.poverty.ProfileFragment
import com.example.poverty.R

private val TAB_TITLES = arrayOf(
    R.string.tab_text_1,
    R.string.tab_text_2,
    "Tab 3"
)

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        //return PlaceholderFragment.newInstance(position + 1)

        when (position){
            0-> {
                return HomeFragment();
            }
            1->{
                return DonateFragment();
            }
            2->{
                return ProfileFragment();
            }
            else->{
                return HomeFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        //return context.resources.getString(TAB_TITLES[position])
        when (position){
            0->{
                return "HOME";
            }
            1 -> {
                return "DONATE";
            }
            2 -> {
                return "PROFILE";
            }

        }
        return null
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return 3
    }
}