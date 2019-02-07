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

import android.content.Context
import android.os.Bundle
import android.support.v4.app.ListFragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView

/**
 * @author Paul Burke (ipaulpro)
 */
class MainFragment : ListFragment() {

    private var itemClickListener: OnListItemClickListener? = null

    interface OnListItemClickListener {
        fun onListItemClick(position: Int)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        itemClickListener = context as OnListItemClickListener?
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val items = resources.getStringArray(R.array.main_items)
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1, items
        )
        listAdapter = adapter
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        itemClickListener!!.onListItemClick(position)
    }
}
