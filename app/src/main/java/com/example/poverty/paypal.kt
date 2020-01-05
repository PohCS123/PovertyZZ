package com.example.poverty

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_paypal.*


class paypal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paypal)

        buttonOK.setOnClickListener {
            if (editTextemailll.text.isEmpty()) {
                editTextemailll.setError((getString(R.string.erroremail)))

            }
            if (editTextpss.text.isEmpty()) {
                editTextpss.setError((getString(R.string.errorpassword)))

            } else {

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
        buttonCancel1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            // start your next activity
            startActivity(intent)
        }
    }
}

