package com.example.poverty

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_second.*

class second : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)


        buttondonate.setOnClickListener {
            // build alert dialog
            if(editTextcard.text.isEmpty()){
                editTextcard.setError((getString(R.string.errorcard)))

            }
            if(editTexcvv.text.isEmpty()){
                editTexcvv.setError((getString(R.string.errorcvv)))

            }
            if(editText4.text.isEmpty()){
                editText4.setError((getString(R.string.errorpostal)))

            }
            if(editTextcontact.text.isEmpty()){
                editTextcontact.setError((getString(R.string.errorcontact)))

            }
            else {

                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Donate Sucessfully,thank you")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Finish", DialogInterface.OnClickListener { dialog, id ->
                        finish()
                        val intent = Intent(this, MainActivity::class.java)
                        // start your next activity
                        startActivity(intent)
                    })
                // negative button text and action

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Donate Sucessfully!!")
                // show alert dialog
                alert.show()
            }
        }
        buttoncancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }

    }
    private fun error(){



    }
}
