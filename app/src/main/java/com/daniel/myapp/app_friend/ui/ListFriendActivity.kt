package com.daniel.myapp.app_friend.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.adapter.RvFriendAdapter
import com.daniel.myapp.app_friend.model.Friend
import com.daniel.myapp.databinding.ActivityHomeBinding
import com.daniel.myapp.databinding.ActivityListFriendBinding
import com.daniel.myapp.the_twin_binding.DetailActivity
import java.io.ByteArrayOutputStream
import androidx.core.graphics.createBitmap
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.daniel.myapp.app_friend.database.MyDatabase
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.ui.viewmodel.FriendVMFactory
import com.daniel.myapp.app_friend.ui.viewmodel.FriendViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListFriendActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListFriendBinding

    private val friendList = ArrayList<FriendEntity>()

    private lateinit var viewModel: FriendViewModel
    private lateinit var adapter: RvFriendAdapter

    private lateinit var database: MyDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
//        setContentView(R.layout.activity_list_friend)

        binding = ActivityListFriendBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModelFactory = FriendVMFactory(this)
        viewModel = ViewModelProvider(this, viewModelFactory)[FriendViewModel::class.java]

        /*val img1 = ResourcesCompat.getDrawable(resources, R.drawable.img_friend, null)
        val img2 = ResourcesCompat.getDrawable(resources, R.drawable.icon_friend_app_1, null)
        val img3 = ResourcesCompat.getDrawable(resources, R.drawable.icon_friend_app_2, null)

        val friends = arrayOf(
            Friend("Budi Pamungkas", "SMKN 11 Semarang", img1),
            Friend("Tifa Lockhart", "SMKN 1 Kendal", img2),
            Friend("Kevin Hart", "SMAN 1 Bergas", img3)
        )*/

        adapter = RvFriendAdapter(
            this,
            { position, data ->
                val destination = Intent(this, ManageFriendActivity::class.java).apply {
                    putExtra("id", data.id)
                }
                startActivity(destination)
                /*val friendPhoto = data.photo?.let { drawableToByteArray(it) }
                val destination = Intent(this, DetailFriendActivity::class.java)
                with(destination) {
                    putExtra("nama", data.name)
                    putExtra("sekolah", data.school)
                    putExtra("foto", friendPhoto)
                }
                startActivity(destination)*/
            }, { position, data ->
                deleteFriend(data)
            }
        )

        binding.rvFriend.adapter = adapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.getFriend().collect { friends ->
                        friendList.clear()
                        friendList.addAll(friends)
                        adapter.setData(friendList)
                    }
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, ManageFriendActivity::class.java)
            startActivity(intent)
        }

    }

    private fun deleteFriend(data: FriendEntity) {
        lifecycleScope.launch {
//            friendList.remove(data)
            viewModel.deleteFriend(data)
//            adapter.setData(friendList)
        }
    }


    private fun drawableToByteArray(drawable: Drawable): ByteArray {
        var bitmap: Bitmap
        if (drawable is BitmapDrawable) {
            bitmap = drawable.bitmap
        }

        bitmap = createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight)

        val canvas = android.graphics.Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)

        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)

        return stream.toByteArray()
    }
}