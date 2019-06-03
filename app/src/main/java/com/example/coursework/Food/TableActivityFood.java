package com.example.coursework.Food;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.coursework.R;
import com.example.coursework.Weight.WeightDB;

public class TableActivityFood extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_food);

        String tableID = "ID";
        String tableFIO = "КАЛОРИИ";
        String tableTime = "ВРЕМЯ";
        addRow(tableID, tableFIO, tableTime);

        FoodDB dbHelper = new FoodDB(this);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(FoodDB.TABLE_FOOD, null, null, null, null, null, null);

        if (cursor.moveToFirst())
        {
            int idIndex = cursor.getColumnIndex(FoodDB.KEY_ID);
            int fioIndex = cursor.getColumnIndex(FoodDB.KEY_CALORIES);
            int timeIndex = cursor.getColumnIndex(FoodDB.KEY_DATE);
            do {
                addRow(String.valueOf(cursor.getInt(idIndex)), cursor.getString(fioIndex), cursor.getString(timeIndex));
            } while (cursor.moveToNext());
        }
        cursor.close();
    }

    public void addRow(String id, String fio, String time)
    {
        //Сначала найдем в разметке активити саму таблицу по идентификатору
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableFood);
        //Создаём экземпляр инфлейтера, который понадобится для создания строки таблицы из шаблона. В качестве контекста у нас используется сама активити
        LayoutInflater inflater = LayoutInflater.from(this);
        //Создаем строку таблицы, используя шаблон из файла /res/layout/table_row.xml
        TableRow tr = (TableRow) inflater.inflate(R.layout.tablerow, null);

        TextView tv = (TextView) tr.findViewById(R.id.col1);
        tv.setText(id);
        tv = (TextView) tr.findViewById(R.id.col2);
        tv.setText(fio);
        tv = (TextView) tr.findViewById(R.id.col3);
        tv.setText(time);

        tableLayout.addView(tr); //добавляем созданную строку в таблицу
    }
}
