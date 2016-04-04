package com.swimr.ScalaTestAndroid

import java.util

import android.app.Activity
import android.content.{Context, Intent}
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.AdapterView.OnItemClickListener
import android.widget._
import com.swimr.ScalaTestAndroid.scala_android_1.app.R

class TestArrayAdapter(context: Context, textViewResourceId: Int, objects: util.List[String])
		extends ArrayAdapter[String](context, textViewResourceId, objects) {

}

/**
  *
  *
  * Scala replacement for MainActivity_NOT_USED.java. Change AndroidManifest.xml to make this the default view.
  *
  */
class MainScalaActivity extends Activity {

	def openActivity3 = {
		val intent:Intent = new Intent(MainScalaActivity.this, classOf[Activity3])
		val editText = findViewById(R.id.textViewToActivity3).asInstanceOf[EditText]
		val textToPass = editText.getText.toString
		intent.putExtra(Activity3.MESSAGE_EXTRA_KEY, textToPass)
		startActivity(intent)
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
	}

	def openActivity3WithMessage(message:String) = {
		val intent:Intent = new Intent(MainScalaActivity.this, classOf[Activity3])
		intent.putExtra(Activity3.MESSAGE_EXTRA_KEY, message)
		startActivity(intent)
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
	}


	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val myclass: MyScalaClass = new MyScalaClass
		val myScalaString: String = myclass.sayHello
		(findViewById(R.id.myTextview).asInstanceOf[TextView]).setText(myScalaString)

		// Button
		val testButton:Button = findViewById(R.id.button_activity3).asInstanceOf[Button]
		testButton.setOnClickListener(new OnClickListener {
			override def onClick(v: View): Unit = {
				openActivity3
			}
		})


		// Listview
		val listview = findViewById(R.id.listView_mainActivity).asInstanceOf[ListView]
		import scala.collection.JavaConversions._
		val listItems = List[String]("hello","goodbye")
		val adapter = new TestArrayAdapter(this, android.R.layout.simple_list_item_1, listItems)
		listview.setAdapter(adapter)
		listview.setOnItemClickListener(new OnItemClickListener {
			override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) = {
				val itemString:String = parent.getItemAtPosition(position).asInstanceOf[String]
				openActivity3WithMessage(itemString)
			}
		})

	}
}
