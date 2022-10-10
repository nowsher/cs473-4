package com.example.myapplication

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var userList: ArrayList<UserAccount> = arrayListOf<UserAccount>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)//.shop_categories //create_account//activity_main//calculator

        userList.add(UserAccount(1, "nowsher", "ali", "nowsher@asd.asd", "1234"))
        userList.add(UserAccount(2, "nowsher1", "ali", "nowsher1@asd.asd", "1234"))
        userList.add(UserAccount(3, "nowsher2", "ali", "nowsher2@asd.asd", "1234"))
        userList.add(UserAccount(4, "nowsher3", "ali", "nowsher3@asd.asd", "1234"))
        userList.add(UserAccount(5, "sa", "sa", "sa@asd.asd", "admin"))

        //setting default for test purpose
        editTextEmail.setText("nowsher@asd.asd")
        editTextPassword.setText("1234")

        //////////////////////////////
        buttonLogin.setOnClickListener {

            var tmpName: String = editTextEmail.text.toString().trim()
            var tmpPass: String = editTextPassword.text.toString().trim()

            if (tmpName.isNullOrEmpty()) {
                editTextEmail.error = "Please set a valid email."
            } else
                if (tmpPass.isNullOrEmpty()) {
                    editTextPassword.error = "Please set valid password."
                }

            if (!verifyUser(tmpName, tmpPass)) {
                Toast.makeText(this, "User not exist.", Toast.LENGTH_LONG).show()
            } else {
//                editTextEmail.text.clear()
//                editTextPassword.text.clear()

                val intent = Intent(this, ShopCategoriesActivity::class.java)
                intent.putExtra("email", tmpName)
                startActivity(intent);
            }

        }

        textViewForgot.setOnClickListener {

            var tmpEmail: String = editTextEmail.text.toString().trim()
            if (tmpEmail.isNullOrEmpty()) {
                editTextEmail.error = "Invalid email"
                return@setOnClickListener
            }
            var userAccount: UserAccount? = userList.find { s -> s.userEmail == tmpEmail }


            //Email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                type = "*/*"    //"text/plain"
                data = Uri.parse("mailto:mohammad.ali@miu.edu")
                putExtra(Intent.EXTRA_SUBJECT, "Check your password")
                putExtra(Intent.EXTRA_TEXT, "Your password is :" + userAccount?.password)
            }
            startActivity(intent)


        }

        var resultContracts =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
//                    var v: String = result.data?.data.toString()

                    val temp = result.data?.getSerializableExtra("user")
                    var userAccount = temp as UserAccount
                    if (userAccount != null){
                        userList.add(userAccount)
                    }
                    Toast.makeText(this,"User created successfully", Toast.LENGTH_LONG)
                } else {
                    Toast.makeText(this,"User creation failed!", Toast.LENGTH_LONG)
                }
            }

        buttonCreate.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
//            startActivity(intent);
            resultContracts.launch(intent)
        }

    }

    fun verifyUser(email: String, password: String): Boolean {
        return userList.contains(UserAccount(0, "", "", email, password))
    }

}