package com.example.demoapp.fragments

import CustomProgressDialog
import android.os.Bundle
import android.text.method.MetaKeyKeyListener
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.demoapp.R
import com.example.demoapp.pojos.RegisterResponse
import com.example.demoapp.retrofit.APIService
import com.example.demoapp.retrofit.RetrofitInstance
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    val progressDialog = CustomProgressDialog()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val submitButton: Button = view.findViewById(R.id.btn_submit)
        val txtName: EditText = view.findViewById(R.id.txt_name)
        val txtEmail: EditText = view.findViewById(R.id.txt_email)
        val txtIsd: EditText = view.findViewById(R.id.txt_isd)
        val txtMobileNumber: EditText = view.findViewById(R.id.txt_mobile_number)
        val txtPassword: EditText = view.findViewById(R.id.txt_password)
        val txtConfirmPassword: EditText = view.findViewById(R.id.txt_confirm_password)
        val txtUsertype: EditText = view.findViewById(R.id.txt_user_type)


        submitButton.setOnClickListener {
            validation(
                txtName.text.toString().trim(),
                txtEmail.text.toString().trim(),
                txtIsd.text.toString().trim(),
                txtMobileNumber.text.toString().trim(),
                txtPassword.text.toString().trim(),
                txtConfirmPassword.text.toString().trim(),
                txtUsertype.text.toString().trim()
            )
        }


    }

    private fun validation(
        name: String,
        email: String,
        isd: String,
        mobileno: String,
        password: String,
        confirm_password: String,
        usertype: String
    ) {
        when {
            name.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid Name !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            email.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid Email !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            isd.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid ISD code!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            mobileno.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid Mobile Number !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            password.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid Password !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            confirm_password.contentEquals("") || !confirm_password.equals(password) -> {
                view?.let {
                    Snackbar.make(it, "Passwords Mismatch !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            usertype.contentEquals("") -> {
                view?.let {
                    Snackbar.make(it, "Invalid User type !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
            else -> {
                progressDialog.show(this.requireContext(),"Saving...")
                saveProfile(name, email, isd, mobileno, password, usertype)
            }
        }
    }

    private fun saveProfile(
        name: String,
        email: String,
        isd: String,
        mobileno: String,
        password: String,
        usertype: String
    ) {
        val apiService: APIService = RetrofitInstance.getInstance()!!.create(APIService::class.java)
        val call = apiService.createProfile(name, email, isd, mobileno, password, usertype)
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>
            ) {
                progressDialog.hideProgressbar()
                if (response.code() == 200) {
                    val registerResponse = response.body()!!
                    view?.let {
                        Snackbar.make(it, registerResponse.data.message, Snackbar.LENGTH_LONG)
                            .setAction("Action", null)
                            .show()
                    }
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                progressDialog.hideProgressbar()
                view?.let {
                    Snackbar.make(it, "Operation failed !", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show()
                }
            }
        })
    }


}