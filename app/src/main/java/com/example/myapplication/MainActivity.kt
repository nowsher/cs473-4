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

//        var tmpView:View = findViewById(R.id.layoutParent)
//        var tEditText:EditText = findViewById(R.id.editTextEmail)
        //Log.i("xxx","yyy")
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



//            //open Dial app
//            val intent = Intent(Intent.ACTION_DIAL)
//            intent.data = Uri.parse("tel:+16418192474")
//            if (intent.resolveActivity(packageManager) != null) {
//                startActivity(intent)
//            }

//            // open URL in browser
//            val uri = Uri.parse("http://www.google.com")
//            val it = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(it)

//            //Map Iowa latitude and longitude
//            val uri = Uri.parse("geo:41.01605390860477, -91.96702008827701") //41.01605390860477, -91.96702008827701 (old 41.8780,93.0977)
//            val it = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(it)

//            //open Email app
//            val intent = Intent()
//            intent.action = Intent.ACTION_SENDTO //Intent.ACTION_SEND // to send SMS
//            intent.type = "*/*"
//            intent.putExtra(Intent.EXTRA_EMAIL, "mohammad.ali@miu.edu")
//            intent.putExtra(Intent.EXTRA_SUBJECT, "Check your password")
//            intent.putExtra(Intent.EXTRA_TEXT, "Your password is :" + userAccount?.password)
//            startActivity(intent)


            //Email
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                type = "*/*"    //"text/plain"
                data = Uri.parse("mailto:mohammad.ali@miu.edu")
                putExtra(Intent.EXTRA_SUBJECT, "Check your password")
                putExtra(Intent.EXTRA_TEXT, "Your password is :" + userAccount?.password)
            }
            startActivity(intent)

//            try {
//                startActivity(Intent.createChooser(intent, "Send mail..."))
//                finish()
//                Toast.makeText(this@MainActivity,"Finished sending email...",Toast.LENGTH_LONG).show()
//            } catch (ex: ActivityNotFoundException) {
//                Toast.makeText(this@MainActivity,"There is no email client installed.",Toast.LENGTH_SHORT).show()
//            }


//            //SMS
//            val intent = Intent()
//            intent.action = Intent.ACTION_SEND
//            intent.type = "text/plain"
//            intent.putExtra(Intent.EXTRA_TEXT, "my message")
//            startActivity(intent)

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