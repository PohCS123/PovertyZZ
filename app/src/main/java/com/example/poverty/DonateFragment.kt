package com.example.poverty


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_donate.*

/**
 * A simple [Fragment] subclass.
 */
class DonateFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val v = inflater.inflate(R.layout.fragment_donate, container, false)


        val five = v.findViewById<View>(R.id.buttonfive) as Button
        five.setOnClickListener {
            editTextAmount.setText("5.00");
            textViewPAY.text = five.text;
        }
        val ten = v.findViewById<View>(R.id.buttonten) as Button
        ten.setOnClickListener {
            editTextAmount.setText("10.00");
            textViewPAY.text = ten.text;
        }
        val fifty = v.findViewById<View>(R.id.buttonfifty) as Button
        fifty.setOnClickListener {
            editTextAmount.setText("50.00");
            textViewPAY.text = fifty.text;
        }

        val textView = v.findViewById<View>(R.id.editTextAmount) as TextView
        textView.setOnClickListener {
            textViewPAY.text = textView.text
        }

        val credit = v.findViewById<View>(R.id.buttoncredit) as Button
        credit.setOnClickListener {
            val intent = Intent(requireActivity(), second::class.java)
            // start your next activity
            requireActivity().startActivity(intent)
        }
        val paypal1 = v.findViewById<View>(R.id.buttononline) as Button
        paypal1.setOnClickListener {
            val intent = Intent(requireActivity(), paypal::class.java)
            // start your next activity
            requireActivity().startActivity(intent)
        }
        val pay = v.findViewById<View>(R.id.buttonPay) as Button
        pay.setOnClickListener {
            val intent = Intent(requireActivity(), paypal::class.java)
            // start your next activity
            requireActivity().startActivity(intent)
        }


        return v
        // Inflate the layout for this fragment

    }
}
