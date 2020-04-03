package th.ac.kku.cis.rescue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        val name = getIntent().getExtras()!!.getString("name")
        val id = getIntent().getExtras()!!.getString("id")
        val sex = getIntent().getExtras()!!.getString("sex")
        val detail = getIntent().getExtras()!!.getString("detail")

        textView3.text="ชื่อ : "+name
        textView4.text="รหัสนักศึกษา : "+id
        textView5.text="เพศ : "+sex
        textView6.text="รายละเอียด : "+detail
    }
}
