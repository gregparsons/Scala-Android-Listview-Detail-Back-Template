package com.swimr.ScalaTestAndroid

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.{Menu, MenuItem}
import android.widget.TextView
import com.swimr.ScalaTestAndroid.scala_android_1.app.R

object Activity3{
	val MESSAGE_EXTRA_KEY = "messageExtra"
}

class Activity3 extends Activity {
	val TAG = "Activity3"

	protected override def onCreate(savedInstanceState: Bundle) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_3)

		val tv = findViewById(R.id.textview_activity3).asInstanceOf[TextView]
		val intent = getIntent
		val message = intent.getStringExtra(Activity3.MESSAGE_EXTRA_KEY)
		tv.setText(message)


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



}
