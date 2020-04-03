package th.ac.kku.cis.rescue

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    lateinit var mDatabase: DatabaseReference
    var toDoItemList: MutableList<model>? = null
    lateinit var adapter: InstrumentAdapter
    private var listViewItems: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        mDatabase = FirebaseDatabase.getInstance().reference
        mDatabase.orderByKey().addListenerForSingleValueEvent(itemListener)

        listViewItems = findViewById<View>(R.id.list_view) as ListView

        toDoItemList = mutableListOf()
        adapter = InstrumentAdapter(this, toDoItemList!!)
        listViewItems!!.setAdapter(adapter)

        button.setOnClickListener {
            startActivity(Intent(this@Main2Activity,Main3Activity::class.java))
            finish()
        }

        list_view.setOnItemClickListener{parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position) as model
            Toast.makeText(this,selectedItem.name, Toast.LENGTH_SHORT).show()
            val intent = Intent(this, Main4Activity::class.java)
            intent.putExtra("name",selectedItem.name)
            intent.putExtra("id",selectedItem.id)
            intent.putExtra("detail",selectedItem.detail)
            intent.putExtra("credit",selectedItem.credit)
            startActivity(intent)
        }

    }
    var itemListener: ValueEventListener = object : ValueEventListener {

        override fun onDataChange(dataSnapshot: DataSnapshot) {
            // call function
            addDataToList(dataSnapshot.child("case"))
        }

        override fun onCancelled(databaseError: DatabaseError) {
            // Getting Item failed, display log a message
            Log.w("MainActivity", "loadItem:onCancelled", databaseError.toException())
        }
    }
    private fun addDataToList(dataSnapshot: DataSnapshot) {
        val items = dataSnapshot.children.iterator()
        // Check if current database contains any collection
        // check if the collection has any to do items or not
        if (items.hasNext()){
            while (items.hasNext()) {
                // get current item
                val todo = model.create()
                val currentItem = items.next()
                val map = currentItem.getValue() as HashMap<String, Any>
                // add data to object
                todo.name = map["name"] as String
                todo.id = map["id"] as String
                todo.detail = map["detail"] as String
                todo.credit = map["credit"] as String
                toDoItemList!!.add(todo)
                adapter.notifyDataSetChanged()
            }
        }
    }
}
