/*
 * Copyright (C) 2015 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.paulburke.android.itemtouchhelperdemo

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import co.paulburke.android.itemtouchhelperdemo.helper.ItemTouchHelperAdapter
import co.paulburke.android.itemtouchhelperdemo.helper.ItemTouchHelperViewHolder
import co.paulburke.android.itemtouchhelperdemo.helper.OnStartDragListener
import java.util.ArrayList
import java.util.Arrays
import java.util.Collections

/**
 * Simple RecyclerView.Adapter that implements [ItemTouchHelperAdapter] to respond to move and
 * dismiss events from a [android.support.v7.widget.helper.ItemTouchHelper].
 *
 * @author Paul Burke (ipaulpro)
 */
class RecyclerListAdapter(context: Context, private val mDragStartListener: OnStartDragListener) :
    RecyclerView.Adapter<RecyclerListAdapter.ItemViewHolder>(), ItemTouchHelperAdapter {

    private val mItems = ArrayList<String>()

    init {
        mItems.addAll(Arrays.asList(*context.resources.getStringArray(R.array.dummy_items)))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
        return ItemViewHolder(view)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = mItems[position]

        // Start a drag whenever the handle view it touched
        holder.handleView.setOnTouchListener { v, event ->
            if (event.actionMasked == MotionEvent.ACTION_DOWN) {
                mDragStartListener.onStartDrag(holder)
            }
            false
        }
    }

    override fun onItemDismiss(position: Int) {
        mItems.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        Collections.swap(mItems, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
        return true
    }

    override fun getItemCount(): Int {
        return mItems.size
    }

    /**
     * Simple example of a view holder that implements [ItemTouchHelperViewHolder] and has a
     * "handle" view that initiates a drag event when touched.
     */
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        ItemTouchHelperViewHolder {

        val textView: TextView = itemView.findViewById<View>(R.id.text) as TextView
        val handleView: ImageView = itemView.findViewById<View>(R.id.handle) as ImageView

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }
}
