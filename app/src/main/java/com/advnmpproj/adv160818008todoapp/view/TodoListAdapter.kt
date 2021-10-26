package com.advnmpproj.adv160818008todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.advnmpproj.adv160818008todoapp.R
import com.advnmpproj.adv160818008todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_layout.view.*

class TodoListAdapter(val todoList:ArrayList<Todo>, val adapterOnCLick:(Any)->Unit):RecyclerView.Adapter<TodoListAdapter.TodoListViewHolder>() {
    class TodoListViewHolder(var view:View):RecyclerView.ViewHolder(view)

    fun updateTodoList(newTodoList:List<Todo>){
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.todo_item_layout, parent,false)

        return TodoListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoListViewHolder, position: Int) {
        with(holder.view)
        {
            if(todoList[position].is_done == 0) {
                checkTodo.visibility = View.VISIBLE
                imgEdit.visibility = View.VISIBLE
            }
            else {
                checkTodo.visibility = View.GONE
                imgEdit.visibility = View.GONE
            }
            checkTodo.text = todoList[position].title

            imgEdit.setOnClickListener{
                val action = TodoListFragmentDirections.actionEditTodoFragment(todoList[position].uuid)
                Navigation.findNavController(it).navigate(action)
            }

            checkTodo.setOnCheckedChangeListener { compoundButton, isChecked ->
                if (isChecked) {
//                    adapterOnCLick(todoList[position])
                    adapterOnCLick(todoList[position].uuid)
                    checkTodo.visibility = View.GONE
                    imgEdit.visibility = View.GONE
                }
            }
        }
    }

    override fun getItemCount(): Int {
       return todoList.size
    }
}