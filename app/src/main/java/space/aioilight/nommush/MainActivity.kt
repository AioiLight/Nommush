package space.aioilight.nommush

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button_web).setOnClickListener {
            val url = "https://racing-lagoon.info/nomu/translate.php"
            val uri = Uri.parse(url)
            val intents = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intents)
        }

        findViewById<Button>(R.id.button_ime).setOnClickListener {
            val imeManager = applicationContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imeManager.showInputMethodPicker()
        }
    }
}