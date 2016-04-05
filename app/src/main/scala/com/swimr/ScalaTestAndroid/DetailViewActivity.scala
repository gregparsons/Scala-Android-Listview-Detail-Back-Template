package com.swimr.ScalaTestAndroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.{View, Menu, MenuItem}
import android.widget.AdapterView.{OnItemSelectedListener, OnItemClickListener}
import android.widget.{AdapterView, ArrayAdapter, Spinner, TextView}
import com.swimr.ScalaTestAndroid.scala_android_1.app.R

object DetailViewActivity{
	val MESSAGE_EXTRA_KEY = "messageExtra"
}

class DetailViewActivity extends Activity with OnItemSelectedListener{
	val TAG = "Activity3"

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_detail_view)


		// Textview: show message from parent activity
		val tv = findViewById(R.id.textview_detail_view).asInstanceOf[TextView]
		val intent = getIntent
		val message = intent.getStringExtra(DetailViewActivity.MESSAGE_EXTRA_KEY)
		tv.setText(message)

		// Spinner show something
		val spinner = findViewById(R.id.spinner_detail_view).asInstanceOf[Spinner]
		val valuesList = List[String]("Spinner1", "Spinner2")
//		import scala.collection.JavaConversions._
		val spinnerArrayAdapter = new ListviewArrayAdapter(this, valuesList)
		spinner.setAdapter(spinnerArrayAdapter)
		spinner.setOnItemSelectedListener(this)

	}

	override def onCreateOptionsMenu(menu: Menu): Boolean = {
		getMenuInflater.inflate(R.menu.menu_activity3, menu)
		return true
	}

	override def onOptionsItemSelected(item: MenuItem): Boolean = {
		val id: Int = item.getItemId

		Log.d(TAG, s"[onOptionsItemSelected] Item selected: ${id}")

		id match {

			case i if i == R.id.action_settings => {
				overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right)
				return true
			}

			case android.R.id.home => {
				onBackPressed
				return true
			}

		}
		return super.onOptionsItemSelected(item)
	}

	/**
	  * Back hardware button pressed. Doesn't fire when you hit the left arrow button in the actionbar.
	  */
	override def onBackPressed = {
		super.onBackPressed
		overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right)
	}


//
//	// onitemclickedlistener
//	override def onItemClick(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
//		position match {
//			case x:Int => Log.d(TAG, s"${x} clicked")
//		}
//	}

	// ***** OnItemSelectedListener *****
	override def onNothingSelected(parent: AdapterView[_]): Unit = {}

	override def onItemSelected(parent: AdapterView[_], view: View, position: Int, id: Long): Unit = {
		Log.d(TAG, s"${position} clicked")
		//		position match {
//			case x:Int => Log.d(TAG, s"${x} clicked")
//		}
	}
}
