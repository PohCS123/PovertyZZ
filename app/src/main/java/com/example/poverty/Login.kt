package com.example.poverty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        button_CreateNewAccount.setOnClickListener {
            val intent = Intent(this, register::class.java)
            startActivityForResult(intent, 1)
        }

        button_Login.setOnClickListener {
            login()
        }


    }

    private fun login() {
        val inEmail = editText_Email.text.toString()
        val inPassword = editText_Password.text.toString()
        val url = getString(R.string.url_server) + getString(R.string.url_user_read_one)  +
                "?email=" + inEmail +
                "&password=" + inPassword



        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                // Process the JSON
                try{
                    if(response != null){
                        val strResponse = response.toString()
                        val jsonResponse  = JSONObject(strResponse)


                        var jsonUser: JSONObject = jsonResponse
                        var user: User = User(jsonUser.getString("name"),
                            jsonUser.getString("email"),
                            jsonUser.getString("password"))


                        Toast.makeText(applicationContext, "Welcome " + jsonUser.getString("name") + "!", Toast.LENGTH_LONG).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivityForResult(intent, 1)
                    }
                }catch (e:Exception){
                    Log.d("Main", "Response: %s".format(e.message.toString()))
                    Toast.makeText(applicationContext, "Wrong email or password!", Toast.LENGTH_LONG).show()
                }
            },
            Response.ErrorListener { error ->
                Log.d("Main", "Response: %s".format(error.message.toString()))
                Toast.makeText(applicationContext, "Please type your email and password", Toast.LENGTH_LONG).show()
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


}
