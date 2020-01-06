package com.example.poverty


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.content.SharedPreferences
import android.util.Log
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_profile.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val sharedPref : SharedPreferences = requireActivity().getSharedPreferences("PREF", Context.MODE_PRIVATE)
        val sharedPref2 : SharedPreferences = requireActivity().getSharedPreferences("PREF2", Context.MODE_PRIVATE)
        val inf = inflater.inflate(R.layout.fragment_profile,container,false)
        val tv = inf.findViewById<TextView>(R.id.textView_proName)
        val tv2 = inf.findViewById<TextView>(R.id.textView_proEmail)
        tv.setText(sharedPref.getString("PREF",""))
        tv2.setText(sharedPref2.getString("PREF2",""))
        Log.d("GG.com",sharedPref.getString("PREF","").toString())
        return inf
    }


}
