package com.example.myapplication


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.adapter.DataAdapter
import com.example.myapplication.model.Data
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


class HomeActivity : AppCompatActivity() {


    private var mRecyclerView: RecyclerView? = null

    var listOfData: MutableList<Data> = mutableListOf()


    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null

    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        mRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView


        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView!!.setHasFixedSize(true)

        // use a linear layout manager

        // use a linear layout manager
        layoutManager = LinearLayoutManager(this)
        mRecyclerView!!.setLayoutManager(layoutManager)

        // specify an adapter (see also next example)

        // specify an adapter (see also next example)
        mAdapter = DataAdapter(this, listOfData)
        mRecyclerView!!.setAdapter(mAdapter)

        addItemsFromJSON()
    }

    @Throws(IOException::class)
    private fun readJSONDataFromFile(): String? {
        var inputStream: InputStream? = null
        val builder = StringBuilder()
        try {
            var jsonString: String? = null
            inputStream = resources.openRawResource(R.raw.data)
            val bufferedReader = BufferedReader(
                InputStreamReader(inputStream, "UTF-8")
            )
            while (bufferedReader.readLine().also { jsonString = it } != null) {
                builder.append(jsonString)
            }
        } finally {
            if (inputStream != null) {
                inputStream.close()
            }
        }
        return String(builder)
    }

    private fun addItemsFromJSON() {
        try {
            val jsonDataString = readJSONDataFromFile()
            val jsonArray = JSONArray(jsonDataString)
            for (i in 0 until jsonArray.length()) {
                var itemObj = jsonArray.getJSONObject(i)
                var firstName = itemObj.getString("firstName")
                var lastName = itemObj.getString("lastName")
                var email = itemObj.getString("email")
                var phone = itemObj.getString("phone")
                var data = Data("",firstName,lastName,email,phone)
                listOfData.add(data)

            }
        } catch (e: JSONException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        } catch (e: IOException) {
            Log.d(TAG, "addItemsFromJSON: ", e)
        }
    }
}