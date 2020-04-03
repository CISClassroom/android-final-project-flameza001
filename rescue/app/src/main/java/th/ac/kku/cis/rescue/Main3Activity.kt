package th.ac.kku.cis.rescue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main3.*

class Main3Activity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        mDatabase = FirebaseDatabase.getInstance().reference

        add.setOnClickListener {
            val save = mDatabase.child("case").push()
            val model = model.create()
            model.name = name.text.toString()
            model.id = id.text.toString()
            model.detail= detail.text.toString()
            model.credit = credit.text.toString()
            save.setValue(model)
            Toast.makeText(this,"sucess!!",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this@Main3Activity,Main2Activity::class.java))
            finish()
        }
    }
}
