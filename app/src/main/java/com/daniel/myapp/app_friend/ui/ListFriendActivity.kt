package com.daniel.myapp.app_friend.ui

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.core.graphics.createBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.crocodic.core.base.activity.CoreActivity
import com.crocodic.core.extension.openActivity
import com.daniel.myapp.R
import com.daniel.myapp.app_friend.adapter.RvFriendAdapter
import com.daniel.myapp.app_friend.database.entity.FriendEntity
import com.daniel.myapp.app_friend.ui.viewmodel.FriendViewModel
import com.daniel.myapp.databinding.ActivityListFriendBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream

@AndroidEntryPoint
class ListFriendActivity :
    CoreActivity<ActivityListFriendBinding, FriendViewModel>(R.layout.activity_list_friend) {

    private val friendList = ArrayList<FriendEntity>()

    private lateinit var adapter: RvFriendAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.lifecycleOwner = this

        adapter = RvFriendAdapter(
            this,
            { position, data ->
                /*val destination = Intent(this, ManageFriendActivity::class.java).apply {
                    putExtra("id", data.id)
                }
                startActivity(destination)*/

                openActivity<ManageFriendActivity> {
                    putExtra("id", data.id)
                }
            }, { position, data ->
                deleteFriend(data)
            }
        )

        binding.rvFriend.adapter = adapter

        viewModel.getFriend()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.friends.collect { data ->
                        friendList.clear()
                        friendList.addAll(data)
                        adapter.setData(friendList)
                    }
                }
            }
        }

        binding.btnAdd.setOnClickListener {
            openActivity<ManageFriendActivity>()
            /* val intent = Intent(this, ManageFriendActivity::class.java)
             startActivity(intent)*/
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