package com.example.poccustomview

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat


class MonetaryView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attributeSet, defStyleAttr), Validator {

    private val minimumChar = 2
    private var validatorListener: ValidatorListener? = null
    private val editTextTypeValue: AppCompatEditText by bindView(R.id.view_monetary_type_value)
    private val textViewShowText: AppCompatTextView by bindView(R.id.view_monetary_show_text)

    init {
        inflate(context, R.layout.view_monetary, rootView as ViewGroup?)
        initValidator()
    }

    private fun initValidator() {
        editTextTypeValue.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun afterTextChanged(s: Editable?) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                verifyIsValid()
                validatorListener?.validate()
            }
        })
    }

    private fun verifyIsValid() {
        if (getText().length > minimumChar) {
            if (isValid(getText())) {
                setSuccess(resources.getString(R.string.text_success))
            } else {
                setError(resources.getString(R.string.text_failure))
            }
        }
    }

    fun getText() = editTextTypeValue.text.toString()

    fun setSuccess(textSuccess: String?) {
        changeComponent(textSuccess, android.R.color.holo_green_light)
    }

    fun setError(textError: String?) {
        changeComponent(textError, android.R.color.holo_red_light)
    }

    private fun changeComponent(textComponent: String?, colorInt: Int) {
        with(textViewShowText) {
            setTextColor(ContextCompat.getColor(context, colorInt))
            visibility = View.VISIBLE
            text = textComponent
        }
    }

    override fun isValid(textTyped: String) = textTyped == "123"

    fun setValidatorListener(validatorListener: ValidatorListener) {
        this.validatorListener = validatorListener
    }
}