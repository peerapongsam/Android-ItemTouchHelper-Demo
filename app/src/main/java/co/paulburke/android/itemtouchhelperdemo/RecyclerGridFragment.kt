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

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import co.paulburke.android.itemtouchhelperdemo.helper.OnStartDragListener
import co.paulburke.android.itemtouchhelperdemo.helper.SimpleItemTouchHelperCallback

/**
 * @author Paul Burke (ipaulpro)
 */
class RecyclerGridFragment : Fragment(), OnStartDragListener {

    private lateinit var itemTouchHelper: ItemTouchHelper

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return RecyclerView(container!!.context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listAdapter = RecyclerListAdapter(activity!!, this)
        val callback = SimpleItemTouchHelperCallback(listAdapter)
        itemTouchHelper = ItemTouchHelper(callback)

        (view as RecyclerView).apply {
            setHasFixedSize(true)
            adapter = listAdapter
            layoutManager = GridLayoutManager(
                activity,
                resources.getInteger(R.integer.grid_columns)
            )
            itemTouchHelper.attachToRecyclerView(this)
        }
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewHolder)
    }
}
