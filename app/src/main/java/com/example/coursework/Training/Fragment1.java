package com.example.coursework.Training;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coursework.R;

public class Fragment1 extends Fragment
{

    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";

    int pageNumber;

    static Fragment1 newInstance(int page)
    {
        Fragment1 pageFragment = new Fragment1();
        Bundle arguments = new Bundle();
        arguments.putInt(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(ARGUMENT_PAGE_NUMBER);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, null);

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        switch (pageNumber)
        {
            case 0:
                tvPage.setText("--- Подготовительный цикл ---\n" +
                        "Кол-во микроциклов: 4 (*1 микроцикл = 1 неделя)\n" +
                        "Кол-во тренировок в микроцикле: 4 (Пн-Ср-Пт-Вс)\n" +
                        "Каждый микроцикл увеличение рабочего веса на 5% (~5 кг), т.е. 45-50-55-60% от 1ПМ\n" +
                        "Задача цикла: подготовка организма атлета к будущим нагрузкам в силовом цикле; цикле, направленном на гипертрофию мышечных волокон.\n" +
                        "\n" +
                        "--1 микроцикл:--\n" +
                        "Рабочий вес = 45% от 1ПМ (**ПМ - Подъемный Максимум)\n" +
                        "Кол-во упражнений = 6 \n" +
                        "Темп для каждого упражнения задается следующим образом: XYWZ\n" +
                        "X - время в секундах, затраченное на опускание снаряда\n" +
                        "Y - время в секундах, которое занимает пауза в нижней точке упражнения \n" +
                        "W - время в секундах, затраченное на подъём снаряда (возвращение в исходное положение) \n" +
                        "Z - время в секундах, которое занимает пауза в верхней точке\n" + pageNumber);
                break;
            case 1:
                tvPage.setText("План тренировки:\n" +
                        "- Кардио разминка: 5 минут, пульс 120-145 уд/мин.\n" +
                        "- Суставная разминка: шейный отдел позвоночника, плечевые, локтевые суставы, запястье, тазобедренный, коленный суставы, лодыжки.\n" +
                        "\n" +
                        "1. Отжимания от пола:\n" +
                        "(Постановка рук на ширине плеч)\n" +
                        "Темп: 1011\n" +
                        "*кол-во подходов x кол-во повторений\n" +
                        "3 x 10-12 (отдых между подходами = 1-1,5 мин.)\n" +
                        "\n" +
                        "2. Разгибание голени в тренажере:\n" +
                        "Темп: 2011\n" +
                        "*кол-во подходов x кол-во повторений x рабочий вес\n" +
                        "3 x 12-15 x 40% от 1ПМ (отдых между подходами = 45-60 с.)\n" +
                        "\n" +
                        "3. Тяга верхнего блока широким хватом к груди:\n" +
                        "Темп: 2011\n" +
                        "3 x 12-15 x 40% от 1ПМ (отдых между подходами = 1-1,5 мин.)\n" +
                        "\n" +
                        "4. Приседания:\n" +
                        "(Постановка ног чуть шире ширины плеч)\n" +
                        "Темп: 20X1\n" +
                        "3 x 15-20 (отдых между подходами = 1-1,5 мин.)\n" +
                        "\n" +
                        "5. Жим в тренажере сидя:\n" +
                        "Темп: 1021\n" +
                        "3 x 12-15 x 40% от 1ПМ (отдых между подходами = 1-1,5 мин.)\n" +
                        "\n" +
                        "6. Подтягивания средним прямым хватом:\n" +
                        "Темп: 1011\n" +
                        "3 x 10-12 (отдых между подходами = 1-1,5 мин.)\n" +
                        "\n" +
                        "- Кардио заминка: 10-15 минут, пульс 100-120 уд/мин.\n" +
                        "- Растяжка\n" + pageNumber);
                break;
        }
        return view;
    }
}
