package com.example.poccustomview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.get

class MainActivity : AppCompatActivity(), ValidatorListener {

    private val editTextMonetary : MonetaryView by bindView(R.id.activity_main_type_value)
    private val buttonSend : AppCompatButton by bindView(R.id.activity_main_ok)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupClick()
    }

    private fun setupListener() {
        editTextMonetary.setValidatorListener(this)
    }

    private fun setupClick() {
        buttonSend.setOnClickListener {
            Toast.makeText(
                this,
                getString(R.string.congratulations),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun validate() {
        buttonSend.isEnabled = editTextMonetary.isValid(editTextMonetary.getText())
    }
}