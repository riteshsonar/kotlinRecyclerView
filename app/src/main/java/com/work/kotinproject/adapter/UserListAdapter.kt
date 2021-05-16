package com.work.kotinproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.work.kotinproject.Model.User
import com.work.kotinproject.R
import com.work.kotinproject.utility.loadImage

class UserListAdapter(var users:ArrayList<User>):
    RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {


        fun updateUsers(newUsers: List<User>){
            users.clear()
            users.addAll(newUsers)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= UserViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    class UserViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val imageView = view.findViewById<ImageView>(R.id.imageView);
        private val userName = view.findViewById<TextView>(R.id.name)
        private val userEmail = view.findViewById<TextView>(R.id.email)

        fun bind(country: User){
            userName.text = country.firstName+ "  "+country.lastName
            userEmail.text = country.email
            imageView.loadImage(country.avatar)
        }
    }
}