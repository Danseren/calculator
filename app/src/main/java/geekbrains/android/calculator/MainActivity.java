package geekbrains.android.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_dot, btn_div, btn_mult, btn_subtr, btn_add, btn_result;
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/*
1. С этого урока будем писать приложение «Калькулятор».
    Выберите макет для работы с калькулятором.
    Обоснуйте, почему будете использовать именно этот тип макета.

2. Сверстайте главный экран калькулятора.
    На нём должны быть кнопки, обозначающие цифры и знаки действия: «Плюс», «Умножить», «Разделить», «Вычесть» и т. п.

3. * Добавьте фоновый рисунок для экрана калькулятора.
    Следите, чтобы рисунок был для общего использования. Ресурсы: PxHere, Pixabay.
 */