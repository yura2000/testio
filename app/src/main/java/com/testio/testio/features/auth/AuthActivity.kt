package com.testio.testio.features.auth

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.google.firebase.FirebaseApp
import com.testio.testio.features.main.MainActivity
import com.testio.testio.features.testmain.TestMainActivity
import kotlinx.android.synthetic.main.auth_activity.*


class AuthActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var auth: FirebaseAuth
    private val TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.testio.testio.R.layout.auth_activity)

        sign_in_btn.setOnClickListener(this)
        sign_up_btn.setOnClickListener(this)

        FirebaseApp.initializeApp(this)

        auth = FirebaseAuth.getInstance()
    }


    private fun createAccount(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USER_ID", task.result?.user?.uid)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    authErrorText_tv.text = "Не вдалося авторизуватися. Перевірте правильність введених даних та спробуйте ще раз!"
                    authErrorText_tv.visibility = View.VISIBLE
//                    Toast.makeText(
//
//                        baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                }
            }
    }

    private fun signIn(email: String, password: String) {
        if (!validateForm()) {
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("USER_ID", task.result?.user?.uid)
                    startActivity(intent)
                } else {
                    authErrorText_tv.text = "Не вдалося авторизуватися. Перевірте правильність введених даних та спробуйте ще раз!"
                    authErrorText_tv.visibility = View.VISIBLE
                    // If sign in fails, display a message to the user.
//                    Toast.makeText(
//                        baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }

                if (!task.isSuccessful) {
                    authErrorText_tv.text = "Не вдалося авторизуватися. Перевірте правильність введених даних та спробуйте ще раз!"
                    authErrorText_tv.visibility = View.VISIBLE
//                    Toast.makeText(
//                        baseContext, "Authentication failed.",
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
            }
    }

    private fun validateForm(): Boolean {
        var valid = true

        val email = email_et.text.toString()
        if (TextUtils.isEmpty(email)) {
            email_et.error = "Введіть email!"
            valid = false
        } else {
            email_et.error = null
        }

        val password = password_et.text.toString()
        if (TextUtils.isEmpty(password)) {
            password_et.error = "Введіть пароль!"
            valid = false
        } else {
            password_et.error = null
        }

        return valid
    }

    override fun onClick(v: View) {
        val i = v.id
        when (i) {
            com.testio.testio.R.id.sign_up_btn -> createAccount(email_et.text.toString(), password_et.text.toString())
            com.testio.testio.R.id.sign_in_btn -> signIn(email_et.text.toString(), password_et.text.toString())
        }
    }
}
