package com.example.agecalculator

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //getting today's date from Calender
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        // open date picker when we click on btn
        bd_date_btn.setOnClickListener {
            showDateDialog(year, month, day, edit_text_view_bd)
        }

        // open date picker when we click on btn
        td_date_btn.setOnClickListener {
            showDateDialog(year, month, day, edit_text_view_td)
        }

        //to set today's date
        edit_text_view_td.setText("" + day + "/" + month + "/" + year + "")



        button_age.setOnClickListener {

            var bdInSDF = Date()
            var tdInSDF = Date()

            if (!TextUtils.isEmpty(edit_text_view_bd.text.toString())) {

                bdInSDF = getStringInSDF(edit_text_view_bd.text.toString())

            } else Toast.makeText(this, "Please Enter Date (DD-MM-YYYY)", Toast.LENGTH_SHORT).show()

            if (!TextUtils.isEmpty(edit_text_view_td.toString())){
                tdInSDF = getStringInSDF(edit_text_view_td.text.toString())

            } else Toast.makeText(this, "Please Enter Date (DD-MM-YYYY)", Toast.LENGTH_SHORT).show()

            val diff = tdInSDF.time - bdInSDF.time // in miliseconds

            val totalSeconds = diff / 1000

            test_text_view.text = totalSeconds.toString()


        }



    }






    @RequiresApi(Build.VERSION_CODES.N)
    private fun showDateDialog(year:Int, month:Int, day:Int, text_holder:EditText){
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {datePicker, sYear, sMonth, sDay ->
            val ssMonth = sMonth + 1
//            test_text_view.text = "" + sDay + "/" + ssMonth + "/" + sYear + ""
            text_holder.setText("" + sDay + "/" + ssMonth + "/" + sYear + "")

        },
            year,
            month,
            day)
        datePickerDialog.show()
    }

    private fun getStringInSDF (date: String) : Date{

        val pattern = "dd/M/yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.ENGLISH)

        val dateInSDF = simpleDateFormat.parse(date)

        return  dateInSDF
    }




}