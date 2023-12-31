package com.example.walmart

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.example.walmart.databinding.ActivityShoppingBinding
import com.example.walmart.databinding.CategoryViewBinding

class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding
    private var categoryAdapter: CategoryAdapter?=null
    private var listCategory = ArrayList<Category>()
    private var user: User?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initial 4 categories
        listCategory.add(Category("Electronics", R.drawable.icons8_electronics))
        listCategory.add(Category("Clothing", R.drawable.icons8_clothing))
        listCategory.add(Category("Beauty", R.drawable.icons8_beauty))
        listCategory.add(Category("Food", R.drawable.icons8_food))

        // Interaction between Activity and Layout
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val curIntent = intent // getIntent() in Java
        user = curIntent.getSerializableExtra("user") as User
        binding.username.text = "Welcome " + user!!.username

        categoryAdapter = CategoryAdapter(this, listCategory, user!!)
        binding.gvShopCategories.adapter = categoryAdapter
    }

    class CategoryAdapter(context: Context, private var listCategory: ArrayList<Category>,
                          private var user: User
    ) : BaseAdapter() {
        private var context: Context?= context

        override fun getCount(): Int {
            return listCategory.size
        }

        override fun getItem(position: Int): Any {
            return listCategory[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val categoryViewBinding: CategoryViewBinding = if (convertView == null) {
                // Inflate the layout if it's not recycled
                CategoryViewBinding.inflate(LayoutInflater.from(context), parent, false)
            } else {
                // Use the old view if it exists
                CategoryViewBinding.bind(convertView)
            }

            // Get the category at the current position
            val category = getItem(position) as Category

            // Set the name and image
            categoryViewBinding.categoryName.text = category.categoryName
            categoryViewBinding.categoryImage.setImageResource(category.categoryImageId)

            categoryViewBinding.categoryImage.setOnClickListener{
                Toast.makeText(context, "${category.categoryName} is clicked!", Toast.LENGTH_SHORT).show()

                if (category.categoryName == "Electronics"){
                    val intent = Intent(context, ItemsActivity::class.java)
                    intent.putExtra("user", user)
                    intent.putExtra("categoryName", category.categoryName)
                    context!!.startActivity(intent)
                }
            }

            // Return the bound view
            return categoryViewBinding.root
        }
    }
}
