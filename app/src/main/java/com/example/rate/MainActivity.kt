package com.example.rate

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rate=findViewById<View>(R.id.ratingBar) as RatingBar
        val submit=findViewById<View>(R.id.submit_button)as Button
        submit.setOnClickListener(View.OnClickListener {
            Toast.makeText(this,"Your rating "+rate.rating.toString(),Toast.LENGTH_LONG).show()
        })
        //button click to get input and call sendEmail method
        submit_button.setOnClickListener {
            //get input from EditTexts and save in variables
            val recipient = recipientEt.text.toString().trim()
            val subject = subjectEt.text.toString().trim()
            val message = messageEt.text.toString().trim()

            //method call for email intent with these inputs as parameters
            submit_button(recipient, subject, message)
        }
        
}
        private operator fun Button.invoke(recipient: String, subject: String, message: String) {
            val mIntent = Intent(Intent.ACTION_SEND)
            /*To send an email you need to specify mailto: as URI using setData() method
            and data type will be to text/plain using setType() method*/
            mIntent.data = Uri.parse("mailto:")
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
            //put the Subject in the intent
            mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
            //put the message in the intent
            mIntent.putExtra(Intent.EXTRA_TEXT, message)

            try {

                startActivity(Intent.createChooser(mIntent, "Choose Email Client..."))
            }
            catch (e: Exception){

                //Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
}
