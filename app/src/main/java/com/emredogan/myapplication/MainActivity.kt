package com.emredogan.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import android.widget.Toast
//import jdk.nashorn.internal.runtime.ECMAException.getException
import com.google.firebase.auth.FirebaseUser
//import org.junit.experimental.results.ResultMatchers.isSuccessful
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import com.google.android.gms.tasks.OnCompleteListener
import android.R.attr.password
import android.content.Intent
import android.support.v4.app.FragmentActivity
import android.util.Log


class MainActivity : AppCompatActivity() {

    var emailEditText: EditText? = null
    var passwordEditText: EditText? = null
    val mAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        if (mAuth.currentUser != null) {

            logIn()

        }

    }

    fun goClicked(view: View) {

        Toast.makeText(this,"Button Pressed",Toast.LENGTH_LONG);

        //CHECK IF WE CAN LOGIN IF NOT SIGN UP

        mAuth.signInWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString())
                .addOnCompleteListener(this) { task ->


                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        logIn()
                    } else {
                       //SIGN UP

                        mAuth.createUserWithEmailAndPassword(emailEditText?.text.toString(), passwordEditText?.text.toString()).addOnCompleteListener(this) { task ->


                            if(task.isSuccessful) {
                                logIn()
                                //ADD TO DATABASE
                            } else {
                                Toast.makeText(this,"Login Failed TRY AGAIN",Toast.LENGTH_LONG);
                            }



                        }
                    }

                }

    }

    fun logIn() {

        //NEXT ACTIVITY
        Toast.makeText(this,"Login STARTED",Toast.LENGTH_LONG);

        val intent = Intent(this,SnapsActivity::class.java)
        startActivity(intent)
    }
}
