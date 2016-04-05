package com.swimr.ScalaTestAndroid

import java.util

import android.content.Context
import android.view.{LayoutInflater, ViewGroup, View}
import android.widget.{TextView, ArrayAdapter}
import com.swimr.ScalaTestAndroid.scala_android_1.app.R
import scala.collection.JavaConversions._		// needed to convert the Scala List to java util.List in constructor


//class ListviewArrayAdapter(context: Context, textViewResourceId: Int, objects: util.List[String])
//		extends ArrayAdapter[String](context, textViewResourceId, objects) {

class ListviewArrayAdapter(context: Context, objects: List[String], val resourceId:Int = R.layout.list_item_1)
		extends ArrayAdapter[String](context, resourceId, objects) {

	override def getView(position:Int, convertView:View, parent:ViewGroup):View = {

		// This requires R.layout.list_item_1 (list_item_1.xml) but doesn't specify it by type anywhere.

		val inflater = getContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE).asInstanceOf[LayoutInflater]
		val rowView = inflater.inflate(resourceId,parent,false).asInstanceOf[View]
		val lineOne = rowView.findViewById(R.id.list_item_firstLine).asInstanceOf[TextView]
		val lineTwo = rowView.findViewById(R.id.list_item_secondLine).asInstanceOf[TextView]

		val thisObject = objects.get(position).asInstanceOf[String]
		lineOne.setText(thisObject)
		lineTwo.setText(thisObject)

		rowView

	}

	override def getDropDownView(position:Int, convertView:View, parent:ViewGroup):View = {
		getView(position,convertView,parent)
	}
}