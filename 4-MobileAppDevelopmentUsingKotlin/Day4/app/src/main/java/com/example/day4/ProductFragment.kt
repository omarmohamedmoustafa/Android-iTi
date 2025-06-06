package com.ities45.lab1

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.day4.R

class ProductFragment : Fragment() {
    lateinit var myAdapter: ProductsListAdapter
    lateinit var myRecycler: RecyclerView
    lateinit var myLinearLayoutManager: LinearLayoutManager
    private lateinit var communicator: Communicator

    val dummyProducts = listOf(
        Product(
            id = 1,
            title = "Toyota Camry",
            description = "Reliable midsize sedan with advanced safety features",
            price = 25000,
            rating = 4.7f,
            brand = "Toyota",
            category = "sedans",
            thumbnail = "https://images.pexels.com/photos/17297984/pexels-photo-17297984.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        ),
        Product(
            id = 2,
            title = "Ford Mustang",
            description = "Iconic American muscle car with powerful performance",
            price = 35000,
            rating = 4.8f,
            brand = "Ford",
            category = "sports cars",
            thumbnail = "https://images.pexels.com/photos/112460/pexels-photo-112460.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        ),
        Product(
            id = 3,
            title = "Tesla Model 3",
            description = "Electric sedan with cutting-edge technology",
            price = 40000,
            rating = 4.9f,
            brand = "Tesla",
            category = "electric vehicles",
            thumbnail = "https://images.pexels.com/photos/14634923/pexels-photo-14634923.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        ),
        Product(
            id = 4,
            title = "Honda Civic",
            description = "Compact sedan with sporty design and fuel efficiency",
            price = 22000,
            rating = 4.6f,
            brand = "Honda",
            category = "sedans",
            thumbnail = "https://images.pexels.com/photos/136872/pexels-photo-136872.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        ),
        Product(
            id = 5,
            title = "Chevrolet Silverado",
            description = "Rugged full-size pickup truck for tough jobs",
            price = 38000,
            rating = 4.5f,
            brand = "Chevrolet",
            category = "pickup trucks",
            thumbnail = "https://images.pexels.com/photos/10151516/pexels-photo-10151516.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        ),
        Product(
            id = 6,
            title = "BMW X5",
            description = "Luxury midsize SUV with premium features",
            price = 60000,
            rating = 4.8f,
            brand = "BMW",
            category = "SUVs",
            thumbnail = "https://images.pexels.com/photos/17196409/pexels-photo-17196409.jpeg",
            images = listOf("https://dummyimage.com/400x400/000/fff")
        )
    )

    override fun onAttach(context: Context) {
        super.onAttach(context)
        communicator = context as Communicator
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myRecycler = view.findViewById(R.id.rvProd)
        myLinearLayoutManager = LinearLayoutManager(context)
        myLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        myRecycler.layoutManager = myLinearLayoutManager

        myAdapter = ProductsListAdapter { product ->
            communicator.showProductDetails(product)
        }

        myAdapter.submitList(dummyProducts)
        myRecycler.adapter = myAdapter
    }
}