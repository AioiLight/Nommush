package space.aioilight.nommush

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.jsoup.Connection
import org.jsoup.Jsoup
import java.lang.Exception

class MushroomActivity : AppCompatActivity() {

    private val ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT"
    private val REPLACE_KEY = "replace_key"
    private val NOMLISH_URL = "https://racing-lagoon.info/nomu/translate.php"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mushroom)

        setTitle(R.string.mush_title)

        val action = intent.action
        if (action != null && ACTION_INTERCEPT == action)
        {
            // 呼び出された
            val str = intent.getStringExtra(REPLACE_KEY)

            val edit = findViewById<EditText>(R.id.inputBox)
            edit.setText(str)

            findViewById<Button>(R.id.buttonNomlish).setOnClickListener {
                Thread {
                    val result = convertNomlish(edit.text.toString())
                    runOnUiThread {
                        sendIntent(result)
                    }
                }.start()
            }

            findViewById<Button>(R.id.buttonCancel).setOnClickListener {
                sendIntent(str)
            }
        }
    }

    private fun sendIntent(result: String) {
        val dat = Intent()
        dat.putExtra(REPLACE_KEY, result)
        setResult(Activity.RESULT_OK, dat)
        finish()
    }

    private fun convertNomlish(input: String): String {
        val (token, cookies) = getToken()
        if (token != null && cookies != null)
        {
            val result = getResult(input, token, cookies)

            if (result != null)
            {
                return result
            }
            return input
        }
        return input
    }

    private fun getToken(): Pair<String?, Map<String, String>?> {
        val tokenSelector = "body>form>input[type=hidden]"
        try {
            val response = Jsoup.connect(NOMLISH_URL).execute()
            val document = response.parse()
            val c = response.cookies()

            val token = document.selectFirst(tokenSelector).attr("value").toString()

            return token to c
        } catch (e: Exception) {
            return null to null
        }
    }

    private fun getResult(input: String, token: String, cookies: Map<String, String>): String? {
        val resultSelector = "textarea[name=after1]"
        return try {
            val document = Jsoup.connect(NOMLISH_URL)
                .data("token", token)
                .data("before", input)
                .data("level", "3")
                .data("options", "nochk")
                .data("transbtn", "翻訳")
                .cookies(cookies)
                .method(Connection.Method.POST)
                .post()
            val result = document.selectFirst(resultSelector).text()
            result
        } catch (e: Exception) {
            null
        }
    }
}