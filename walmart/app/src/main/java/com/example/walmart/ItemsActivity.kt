package com.example.walmart

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walmart.databinding.ActivityItemsBinding

class ItemsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityItemsBinding
    private var user: User?=null
    private var cart = ArrayList<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Interaction between Activity and Layout
        binding = ActivityItemsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Products
        val products = ArrayList<Product>()
        products.add(Product("iPad", "iPad Pro 11-inch", 400.0, R.drawable.icons8_ipad, R.drawable.icons8_ipad))
        products.add(Product("MacBook M3 Pro", "12 core CPU", 2500.0, R.drawable.icons8_macbook, R.drawable.icons8_macbook))
        products.add(Product("Dell Inspiron", "13th Gen Intel®", 1499.00, R.drawable.icons8_dell, R.drawable.icons8_dell))
        products.add(Product("Logitech Keyboard", "Logitech PRO X", 199.00, R.drawable.icons8_logitech, R.drawable.icons8_logitech))
        products.add(Product("MacBook M3 Max", "14-core CPU", 3499.00, R.drawable.icons8_macbook, R.drawable.icons8_macbook))
        // Fetch intent
        val curIntent = intent // getIntent() in Java
        user = curIntent.getSerializableExtra("user") as User
        binding.username.text = "Welcome ${user!!.username}"
        binding.categoryName.text = curIntent.getStringExtra("categoryName")

        // Adapter
        var adapter = ItemAdapter(products) { product ->
            cart.add(product)
            Toast.makeText(this, "${product.productName} added to cart", Toast.LENGTH_SHORT).show()
        }
        binding.rvc.layoutManager = GridLayoutManager(this, 1)
        binding.rvc.adapter = adapter

        // view cart
        binding.btnView.setOnClickListener {
            if (cart.isNotEmpty()) {
                val productNames = cart.joinToString(separator = ", ") { it.productName }
                Toast.makeText(this, "Products in cart(${cart.size}): $productNames", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Your cart is empty", Toast.LENGTH_LONG).show()
            }
        }
    }

    class ItemAdapter(private var listItems: ArrayList<Product>, private val onAddToCart: (Product) -> Unit): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ItemAdapter.ItemViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
            return ItemViewHolder(view)
        }

        override fun onBindViewHolder(holder: ItemAdapter.ItemViewHolder, position: Int) {
            val product = listItems[position]
            holder.itemName.text = product.productName
            holder.itemDescription.text = product.productDescription
            holder.itemPrice.text = "$ ${product.cost}"
            holder.imageViewImage.setImageResource(product.imageId)
            holder.imageViewLogo.setImageResource(product.logoId)

            holder.imageViewImage.setOnClickListener{
                Toast.makeText(holder.itemView.context, "${product.productName} is clicked!", Toast.LENGTH_SHORT).show()

                val intent = Intent(holder.itemView.context, ItemDetailsActivity::class.java)
                intent.putExtra("product", product)
                holder.itemView.context!!.startActivity(intent)
            }

            // Set an OnClickListener for the addBtn
            holder.btnAdd.setOnClickListener {
                // Use holder.itemView.context to get the context from the itemView
                Toast.makeText(holder.itemView.context, "${product.productName} added to cart", Toast.LENGTH_SHORT).show()
                onAddToCart(product)
            }
        }

        override fun getItemCount(): Int {
            return listItems.size
        }

        inner class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            var itemName: TextView
            var itemDescription: TextView
            var itemPrice: TextView
            var imageViewImage: ImageView
            var imageViewLogo: ImageView
            var btnAdd: Button = itemView.findViewById(R.id.btnAdd)
            init {
                itemName = itemView.findViewById(R.id.itemName) as TextView
                itemDescription = itemView.findViewById(R.id.itemDescription) as TextView
                itemPrice = itemView.findViewById(R.id.itemPrice) as TextView
                imageViewImage = itemView.findViewById(R.id.itemImage) as ImageView
                imageViewLogo = itemView.findViewById(R.id.itemLogo) as ImageView
            }
        }

    }
}
