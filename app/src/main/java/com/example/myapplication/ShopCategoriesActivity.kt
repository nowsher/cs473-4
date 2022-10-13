package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_shop_categories.*

class ShopCategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_categories)

        val rcvIntent = intent //getIntent()
        val output = rcvIntent.getStringExtra("email")
        textViewWelcome.text = "Welocme $output"

        imageViewElec.setOnClickListener{
            Toast.makeText(this, "You have chosen Electronics.", Toast.LENGTH_LONG).show()
        }
        imageViewCloth.setOnClickListener{
            Toast.makeText(this, "You have chosen Clothing.", Toast.LENGTH_LONG).show()
        }
        imageViewBeauty.setOnClickListener{
            Toast.makeText(this, "You have chosen Beauty.", Toast.LENGTH_LONG).show()
        }
        imageViewFood.setOnClickListener{
            Toast.makeText(this, "You have chosen Food.", Toast.LENGTH_LONG).show()
        }
    }
}