package space.aioilight.nommush

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MushroomActivity : AppCompatActivity() {

    private val ACTION_INTERCEPT = "com.adamrocker.android.simeji.ACTION_INTERCEPT"
    private val REPLACE_KEY = "replace_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mushroom)
    }
}