package com.daniel.myapp.permit

import androidx.activity.enableEdgeToEdge
import com.daniel.myapp.R
import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.daniel.myapp.databinding.ActivityGetContactBinding

class GetContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGetContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityGetContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGetContact.setOnClickListener {
            checkPermissionContact()
        }
    }

    private fun check() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            readContacts()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 100)
        }
    }


    private fun checkPermissionContact() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            readContacts()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 100) {
            if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                readContacts()
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getContacts() {
        var data = ""
        var cData = 0
        val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
        val cursor = contentResolver.query(uri, null, null, null, null)

        if ((cursor?.count ?: 0) > 0) {
            while (cursor?.moveToNext() == true) {
                val name = cursor.getString(
                    cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                        ?: return
                )
                cData++

                val ctn = 290
                if (cData in ctn..ctn + 9) {
                    data += "${cData - ctn + 1} - $name\n\n"
                }
            }
            cursor?.close()

            binding.tvContact.text = data
        } else {
            data = "No Contact Found"
            binding.tvContact.text = data
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show()
        }
    }

    private fun readContacts() {
        val contactsList = mutableListOf<String>()
        val cursor = contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )

        cursor?.use {
            val nameIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
//            val numberIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            while (it.moveToNext()) {
                val name = if (nameIndex >= 0) it.getString(nameIndex) else "Nama Tidak Diketahui"
//                val number = if (numberIndex >= 0) it.getString(numberIndex) else "Nomor Tidak Diketahui"
                contactsList.add("$name")
            }
        }
        Log.d("READ_CONTACTS", "Data: $contactsList")
        var data = ""
        if (contactsList.isNotEmpty()) {
            contactsList.forEachIndexed { index, s ->
                data += "${index + 1} - $s\n\n"
            }
            binding.tvContact.text = data
        } else {
            data = "No Contact Found"
            binding.tvContact.text = data
            Toast.makeText(this, "No Contact Found", Toast.LENGTH_SHORT).show()
        }
    }

}