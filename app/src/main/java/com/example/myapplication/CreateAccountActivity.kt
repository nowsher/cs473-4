package com.example.myapplication

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_account.*
import kotlinx.android.synthetic.main.activity_main.*

class CreateAccountActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        editTextFirstName.setText("qqq")
        editTextLastName.setText("www")
        editTextEmailField.setText("asd@asd.asd")
        editTextPasswordField.setText("1234")

        button.setOnClickListener {

            if (editTextFirstName.text.toString().isNullOrEmpty()) {
                editTextFirstName.error = "Please set a valid first name."
                return@setOnClickListener
            } else
                if (editTextLastName.text.toString().isNullOrEmpty()) {
                    editTextLastName.error = "Please set valid last name."
                    return@setOnClickListener
                }
                else
                    if (editTextEmailField.text.toString().isNullOrEmpty()) {
                        editTextEmailField.error = "Please set valid Email address."
                        return@setOnClickListener
                    }
                    else
                        if (editTextPasswordField.text.toString().isNullOrEmpty()) {
                            editTextPasswordField.error = "Please set valid password."
                            return@setOnClickListener
                        }

            var userAccount: UserAccount = UserAccount(
                0, editTextFirstName.text?.toString(),
                editTextLastName.text?.toString(),
                editTextEmailField.text?.toString(),
                editTextPasswordField.text?.toString()
            )

            var rintent = intent
//            rintent.data = Uri.parse(userAccount) //not needed for class object
            rintent.putExtra("user", userAccount)
            setResult(Activity.RESULT_OK, rintent)
//            Toast.makeText(this,"User created successfully", Toast.LENGTH_LONG)
            finish()
        }

    }


}