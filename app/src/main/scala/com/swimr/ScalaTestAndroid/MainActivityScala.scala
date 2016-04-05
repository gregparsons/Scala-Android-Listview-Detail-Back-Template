package com.swimr.ScalaTestAndroid

import android.app.Activity
import android.content.{Context, Intent}
import android.os.Bundle
import android.view.{LayoutInflater, ViewGroup, View}
import android.view.View.OnClickListener
import android.widget.AdapterView.OnItemClickListener
import android.widget._
import com.swimr.ScalaTestAndroid.scala_android_1.app.R



/**
  *
  *
  * Scala replacement for MainActivity_NOT_USED.java. Change AndroidManifest.xml to make this the default view.
  *
  */
class MainActivityScala extends Activity {

	def openActivity3 = {
		val intent:Intent = new Intent(MainActivityScala.this, classOf[DetailViewActivity])
		val editText = findViewById(R.id.textViewToActivity3).asInstanceOf[EditText]
		val textToPass = editText.getText.toString
		intent.putExtra(DetailViewActivity.MESSAGE_EXTRA_KEY, textToPass)
		startActivity(intent)
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
	}

	def openActivity3WithMessage(message:String) = {
		val intent:Intent = new Intent(MainActivityScala.this, classOf[DetailViewActivity])
		intent.putExtra(DetailViewActivity.MESSAGE_EXTRA_KEY, message)
		startActivity(intent)
		overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
	}


	def createShowDetailButton = {

		// Button
		val testButton:Button = findViewById(R.id.button_activity3).asInstanceOf[Button]
		testButton.setOnClickListener(new OnClickListener {
			override def onClick(v: View): Unit = {
				openActivity3
			}
		})
	}

	def createListview = {

		// Listview
		val listview = findViewById(R.id.listView_mainActivity).asInstanceOf[ListView]
		val listItems = List[String]("hello","goodbye")
		val adapter = new ListviewArrayAdapter(this, listItems)
		listview.setAdapter(adapter)
		listview.setOnItemClickListener(new OnItemClickListener {
			override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long) = {
				val itemString:String = parent.getItemAtPosition(position).asInstanceOf[String]
				openActivity3WithMessage(itemString)
			}
		})

	}


	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		// Prove scala works
		//		val myclass: MyScalaClass = new MyScalaClass
		//		val myScalaString: String = myclass.sayHello
		//		(findViewById(R.id.myTextview).asInstanceOf[TextView]).setText(myScalaString)

		createShowDetailButton

		createListview

	}
}
