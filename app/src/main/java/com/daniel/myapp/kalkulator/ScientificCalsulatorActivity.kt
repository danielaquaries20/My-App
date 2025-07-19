package com.daniel.myapp.kalkulator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.mariuszgromada.math.mxparser.Expression
import com.daniel.myapp.R

class ScientificCalsulatorActivity : AppCompatActivity() {


    private lateinit var inputText: TextView
    private var inputExpression: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_scientific_calsulator)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        inputText = findViewById(R.id.tvInput)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5,
            R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btnAdd, R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnDot, R.id.btnOpen, R.id.btnClose
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener {
                val value = (it as Button).text.toString()
                inputExpression += value
                inputText.text = inputExpression
            }
        }

        findViewById<Button>(R.id.btnClear).setOnClickListener {
            inputExpression = ""
            inputText.text = "0"
        }

        findViewById<Button>(R.id.btnDelete).setOnClickListener {
            if (inputExpression.isNotEmpty()) {
                inputExpression = inputExpression.dropLast(1)
                inputText.text = inputExpression.ifEmpty { "0" }
            }
        }

        /*findViewById<Button>(R.id.btnEquals).setOnClickListener {
            try {
                val result = ScriptEngineManager().getEngineByName("rhino")
                    .eval(inputExpression).toString()
                inputText.text = result
                inputExpression = result
            } catch (e: Exception) {
                inputText.text = "Error"
                inputExpression = ""
            }
        }*/
        findViewById<Button>(R.id.btnEquals).setOnClickListener {
            val expr = Expression(inputExpression)
            val result = expr.calculate()

            if (result.isNaN()) {
                inputText.text = "Error"
                inputExpression = ""
            } else {
                inputText.text = result.toString()
                inputExpression = result.toString()
            }
        }
    }

    /*fun evaluateExpression(expr: String): Double {
        try {
            val tokens = expr.replace(" ", "")
            val engine = android.webkit.WebView(this)
            engine.evaluateJavascript("eval('$tokens')") { result ->
                inputText.text = result
            }
        } catch (e: Exception) {
            inputText.text = "Error"
        }
        return 0.0
    }*/
}