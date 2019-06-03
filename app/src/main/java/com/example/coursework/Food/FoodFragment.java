package com.example.coursework.Food;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coursework.Food.FoodDiagramActivity;
import com.example.coursework.Food.FoodOptimalActivity;
import com.example.coursework.R;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static com.example.coursework.Food.FoodDB.KEY_ID;

public class FoodFragment extends Fragment implements View.OnClickListener
{

    FoodDB foodDB;
    final String[] myProducts = {"Горошек", "Картофель", "Капуста", "Лук репчатый", "Морковь", "Огурец", "Томат", "Апельсин", "Банан", "Яблоко", "Фасоль",
            "Горох", "Хлеб пшеничный", "Хлеб ржаной", "Масло сливочное", "Кефир", "Молоко", "Сыр швейцарский", "Творог полужирный", "Крупа овсяная",
            "Крупа пшеничная", "Сосиски молочные", "Говядина", "Индейка", "Курица", "Свинина", "Яйцо куриное", "Шоколад молочный"};
    final double[] myCalories = {72, 83, 12, 43, 33, 10, 14, 38, 91, 46, 309, 323, 254, 214, 748, 50, 65, 396, 156, 345, 325, 277, 187, 197, 165, 403, 157, 547};
    final double[] myFats = {0.2, 0.1, 0, 0, 0.1, 0, 0, 0, 0, 0, 1.7, 1.6, 2.4, 0.7, 82.5, 2, 3.2, 31.8, 9, 5.8, 1.1, 25.3, 12.4, 12, 8.8, 35.2, 11.5, 35.7};
    final double[] myProteins = {5, 2, 1.2, 1.7, 1.3, 0.7, 0.6, 0.9, 1.5, 0.4, 22.3, 23, 7.7, 4.7, 0.6, 3, 2.8, 24.9, 16.7, 11.9, 12.7, 12.3, 18.9, 21.6, 20.8, 13.8, 12.7, 6.9};
    final double[] myCarbohydrates = {13.3, 19.7, 1.7, 9.5, 7, 1.8, 2.9, 8.4, 22.4, 11.3, 54.5, 57.7, 53.4, 49.8, 0.9, 4, 7, 0, 1.3, 65.4, 70.6, 0, 0, 0.8, 0.6, 0, 0.7, 52.4};

    double dCalories = 0, dFats= 0, dProteins = 0, dCarbohydrates = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_food, container, false);

        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        final String dateText = dateFormat.format(currentDate);

        foodDB = new FoodDB(getActivity());
        final SQLiteDatabase database = foodDB.getWritableDatabase();
        final ContentValues contentValues = new ContentValues();

        Cursor cursor = database.query(FoodDB.TABLE_FOOD, null, null, null, null, null, null);

        String idFromDB = "", caloriesFromDB = "", proteinFromDB = "", fatFromDB = "", carboHydrateFromDB = "", dateTextFromDB = "";

        if (cursor.moveToFirst())
        {
            cursor.moveToLast();
            int idIndex = cursor.getColumnIndex(KEY_ID);
            idFromDB = cursor.getString(idIndex);

            int caloriesIndex = cursor.getColumnIndex(FoodDB.KEY_CALORIES);
            caloriesFromDB = cursor.getString(caloriesIndex);

            int proteinIndex = cursor.getColumnIndex(FoodDB.KEY_PROTEINS);
            proteinFromDB = cursor.getString(proteinIndex);

            int fatIndex = cursor.getColumnIndex(FoodDB.KEY_FAT);
            fatFromDB = cursor.getString(fatIndex);

            int carbohydrateIndex = cursor.getColumnIndex(FoodDB.KEY_CARBOHYDRATE);
            carboHydrateFromDB = cursor.getString(carbohydrateIndex);

            int dateIndex = cursor.getColumnIndex(FoodDB.KEY_DATE);
            dateTextFromDB = cursor.getString(dateIndex);

        }
        else
        {
            contentValues.put(FoodDB.KEY_CALORIES, dCalories);
            contentValues.put(FoodDB.KEY_PROTEINS, dProteins);
            contentValues.put(FoodDB.KEY_FAT, dFats);
            contentValues.put(FoodDB.KEY_CARBOHYDRATE, dCarbohydrates);
            contentValues.put(FoodDB.KEY_DATE, dateText);

            database.insert(FoodDB.TABLE_FOOD, null, contentValues);
        }
        cursor.close();



        final TextView tvCalories = rootView.findViewById(R.id.textViewCalories);
        final TextView tvProteins = rootView.findViewById(R.id.textViewProteins);
        final TextView tvFats = rootView.findViewById(R.id.textViewFats);
        final TextView tvCarbohydtare = rootView.findViewById(R.id.textViewCarbohydrate);

        final AutoCompleteTextView productAutoCompleteTextView = rootView.findViewById(R.id.productTextView);
        final EditText productWeight = rootView.findViewById(R.id.productWeight);

        Button countOptimal = rootView.findViewById(R.id.countOptimalButton);
        Button addProduct = rootView.findViewById(R.id.addProductButton);
        Button checkFood = rootView.findViewById(R.id.dbButton);

        productAutoCompleteTextView.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, myProducts));

        if (dateText.equals(dateTextFromDB))
        {
            dCalories = Double.parseDouble(caloriesFromDB);
            tvCalories.setText("Калории: " + dCalories);

            dProteins = Double.parseDouble(proteinFromDB);
            tvProteins.setText("Белки: " + dProteins);

            dFats = Double.parseDouble(fatFromDB);
            tvFats.setText("Жиры: " + dFats);

            dCarbohydrates = Double.parseDouble(carboHydrateFromDB);
            tvCarbohydtare.setText("Углеводы: " + dCarbohydrates);
        }

        checkFood.setOnClickListener(this);
        countOptimal.setOnClickListener(this);

        addProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor cursor = database.query(FoodDB.TABLE_FOOD, null, null, null, null, null, null);
                String idFromDB = "", caloriesFromDB = "", proteinFromDB = "", fatFromDB = "", carboHydrateFromDB = "", dateTextFromDB = "";

                if (cursor.moveToFirst())
                {
                    cursor.moveToLast();
                    int idIndex = cursor.getColumnIndex(KEY_ID);
                    idFromDB = cursor.getString(idIndex);

                    int caloriesIndex = cursor.getColumnIndex(FoodDB.KEY_CALORIES);
                    caloriesFromDB = cursor.getString(caloriesIndex);

                    int proteinIndex = cursor.getColumnIndex(FoodDB.KEY_PROTEINS);
                    proteinFromDB = cursor.getString(proteinIndex);

                    int fatIndex = cursor.getColumnIndex(FoodDB.KEY_FAT);
                    fatFromDB = cursor.getString(fatIndex);

                    int carbohydrateIndex = cursor.getColumnIndex(FoodDB.KEY_CARBOHYDRATE);
                    carboHydrateFromDB = cursor.getString(carbohydrateIndex);

                    int dateIndex = cursor.getColumnIndex(FoodDB.KEY_DATE);
                    dateTextFromDB = cursor.getString(dateIndex);

                }
                cursor.close();

                int index = Arrays.asList(myProducts).indexOf(productAutoCompleteTextView.getText().toString());
                if (productAutoCompleteTextView.getText().toString().equals("") || productWeight.getText().toString().equals(""))
                {
                    Toast.makeText(getActivity(), "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                else if (index == -1)
                {
                    Toast.makeText(getActivity(), "Такого продукта нет в программе!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int iProductWeight = Integer.parseInt(productWeight.getText().toString());

                    dCalories += (myCalories[index] / 100) * iProductWeight;
                    dCalories = round(dCalories, 1);
                    tvCalories.setText("Калории: " + dCalories);

                    dFats += (myFats[index] / 100) * iProductWeight;
                    dFats = round(dFats, 1);
                    tvFats.setText("Жиры: " + dFats);

                    dProteins += (myProteins[index] / 100) * iProductWeight;
                    dProteins = round(dProteins, 1);
                    tvProteins.setText("Белки: " + dProteins);

                    dCarbohydrates += (myCarbohydrates[index] / 100) * iProductWeight;
                    dCarbohydrates = round(dCarbohydrates, 1);
                    tvCarbohydtare.setText("Жиры: " + dCarbohydrates);

                    Date currentDate = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
                    final String dateText = dateFormat.format(currentDate);

                    if (dateText.equals(dateTextFromDB))
                    {
                        contentValues.put(FoodDB.KEY_CALORIES, dCalories);
                        contentValues.put(FoodDB.KEY_PROTEINS, dProteins);
                        contentValues.put(FoodDB.KEY_FAT, dFats);
                        contentValues.put(FoodDB.KEY_CARBOHYDRATE, dCarbohydrates);

                        String where = KEY_ID + "=" + idFromDB;
                        database.update(FoodDB.TABLE_FOOD, contentValues, where, null);
                    }
                    else
                    {
                        contentValues.put(FoodDB.KEY_CALORIES, dCalories);
                        contentValues.put(FoodDB.KEY_PROTEINS, dProteins);
                        contentValues.put(FoodDB.KEY_FAT, dFats);
                        contentValues.put(FoodDB.KEY_CARBOHYDRATE, dCarbohydrates);
                        contentValues.put(FoodDB.KEY_DATE, dateText);

                        database.insert(FoodDB.TABLE_FOOD, null, contentValues);
                    }
                }
            }
        });

        return rootView;
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.countOptimalButton:
                Intent intent2 = new Intent(getActivity(), FoodOptimalActivity.class);
                getActivity().startActivity(intent2);
                break;
            case R.id.dbButton:
                Intent intent3 = new Intent(getActivity(), FoodDiagramActivity.class);
                getActivity().startActivity(intent3);
                break;
        }
    }

    private static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
