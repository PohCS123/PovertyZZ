package com.example.poverty

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_register.*

class register : AppCompatActivity() {
    private val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textView_AlreadyHaveAnAcc.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
        button_register.setOnClickListener {
            if (editText_r_password.text.toString().equals(editText_r_retype.text.toString())){
                saveUser()
            } else {
                Toast.makeText(applicationContext, "Your password retyped wrongly!", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun saveUser(){

        if(TextUtils.isEmpty(editText_r_username.text)){
            editText_r_username.setError(getString(R.string.error_value_required))
            return
        }
        if(TextUtils.isEmpty(editText_r_email.text)){
            editText_r_email.setError(getString(R.string.error_value_required))
            return
        }
        if(TextUtils.isEmpty(editText_r_password.text)){
            editText_r_email.setError(getString(R.string.error_value_required))
            return
        }
        val name = editText_r_username.text.toString()
        val email = editText_r_email.text.toString()
        val password = editText_r_password.text.toString()

        val intent = Intent(this, register::class.java)
        intent.putExtra(EXTRA_NAME, name)
        intent.putExtra(EXTRA_EMAIL, email)
        intent.putExtra(EXTRA_PASS, password)

        //setResult(Activity.RESULT_OK, intent)
        onActivityResult(1,Activity.RESULT_OK,intent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE){
            if(resultCode == Activity.RESULT_OK){
                data?.let{
                    val user = User(it.getStringExtra(register.EXTRA_NAME), it.getStringExtra(register.EXTRA_EMAIL) , it.getStringExtra(register.EXTRA_PASS))
                    createUser(user)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun createUser(user: User) {
        val url = getString(R.string.url_server) + getString(R.string.url_user_create) + "?name=" + user.name +
                "&email=" + user.email +
                "&password=" + user.password


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Process the JSON
                try{
                    if(response != null){
                        val success: String = response.get("success").toString()

                        if(success.equals("1")){
                            Toast.makeText(applicationContext, "Successfully registered", Toast.LENGTH_LONG).show()

                        }else{
                            Toast.makeText(applicationContext, "Failed to register", Toast.LENGTH_LONG).show()
                        }
                    }
                }catch (e:Exception){
                    Log.d("Main", "Response: %s".format(e.message.toString()))

                }
            },
            Response.ErrorListener { error ->
                Log.d("Main", "Response: %s".format(error.message.toString()))
            }
        )

        //Volley request policy, only one time request
        jsonObjectRequest.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
            0, //no retry
            1f
        )

        // Access the RequestQueue through your singleton class.
        jsonObjectRequest.tag = MainActivity.TAG
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)

    }

    companion object{
        const val EXTRA_NAME = "com.example.poverty.NAME"
        const val EXTRA_EMAIL = "com.example.poverty.EMAIL"
        const val EXTRA_PASS = "com.example.poverty.PASS"
    }

}
