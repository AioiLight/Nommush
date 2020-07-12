package space.aioilight.nommush

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MushroomActivity : AppCompatActivity() {

    private val ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT"
    private val REPLACE_KEY = "replace_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mushroom)

        val action = intent.action
        if (action != null && ACTION_INTERCEPT == action)
        {
            // 呼び出された
            val str = intent.getStringExtra(REPLACE_KEY)

            val edit = findViewById<EditText>(R.id.inputBox)
            edit.setText(str)

            findViewById<Button>(R.id.buttonNomlish).setOnClickListener {
                val result = convertNomlish(edit.text.toString())
                sendIntent(result)
            }

            findViewById<Button>(R.id.buttonCancel).setOnClickListener {
                sendIntent(str)
            }
        }
    }

    fun sendIntent(result: String) {
        val dat = Intent()
        dat.putExtra(REPLACE_KEY, result)
        setResult(Activity.RESULT_OK, dat)
        finish()
    }

    fun convertNomlish(input: String) : String {
        return input
    }
}