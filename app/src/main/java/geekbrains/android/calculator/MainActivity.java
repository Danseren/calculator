package geekbrains.android.calculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_dot, btn_div, btn_mult, btn_subtr, btn_add, btn_result, btn_clear;
    private TextView tv_result, tv_History;
    private Calculator calc = new Calculator();

    private String calcTag = "calcTag";
    private String tvResultTag = "tvResTag";

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        innitViews();

    }

    //Доработать логику действий. Не забыть что после равно нажимая цифры нужно обнулять tv_result
    //Не забыть добавить сохранение данных как было на уроке

    private void innitViews() {

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_dot = findViewById(R.id.btn_dot);
        btn_div = findViewById(R.id.btn_div);
        btn_mult = findViewById(R.id.btn_mult);
        btn_subtr = findViewById(R.id.btn_subtr);
        btn_add = findViewById(R.id.btn_add);
        btn_result = findViewById(R.id.btn_result);
        btn_clear = findViewById(R.id.btn_clear);

        tv_result = findViewById(R.id.tv_result);
        tv_History = findViewById(R.id.tv_History);

        initOnClickListeners();
    }

    private void initOnClickListeners() {

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_subtr.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_result.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putParcelable(calcTag, calc);
        instanceState.putString(tvResultTag, String.valueOf(tv_result.getText()));
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        calc = instanceState.getParcelable(calcTag);
        result = instanceState.getString(tvResultTag, result);
        tv_result.setText(result);
    }

    private void btnSymbolsWork(String a) {

        switch (tv_result.getText().length()) {
            case 0:
                if (a.equals(".")) {
                    calc.setHistory("0.");
                    tv_result.setText(calc.getHistory());
                    Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                } else {
                    calc.setHistory(a);
                    tv_result.setText(calc.getHistory());
                    Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                }
                break;
            case 1:
                calc.setHistory(calc.getHistory() + a);
                tv_result.setText(calc.getHistory());
                Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                break;
            default:
                if (a.equals(".")) {
                    int counter = 0;
                    for (int i = 0; i < tv_result.getText().length(); i++) {
                        if (tv_result.getText().charAt(i) == '.') {
                            counter++;
                        }
                    }
                    if (counter > 0) {
                        Toast.makeText(MainActivity.this, "Давай не будем использовать больше одной точки, Ок?", Toast.LENGTH_SHORT).show();
                    } else {
                        calc.setHistory(calc.getHistory() + a);
                        tv_result.setText(calc.getHistory());
                        Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    calc.setHistory(calc.getHistory() + a);
                    tv_result.setText(calc.getHistory());
                    Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                }
        }

    }

    public boolean checkByNull(String a) {
        return a.length() == 0;
    }

    public void operation1(String str) {

        calc.setSecondNumber(Double.parseDouble(String.valueOf(tv_result.getText())));

        switch (str) {
            case "/":
                calc.setLastResult(String.valueOf(calc.getFirstNumber() / calc.getSecondNumber()));
                calc.setHistory(calc.getHistory() + "/" +  calc.getSecondNumber() + "=" + calc.getLastResult());
                break;
            case "*":
                calc.setLastResult(String.valueOf(calc.getFirstNumber() * calc.getSecondNumber()));
                calc.setHistory(calc.getHistory() + "*" +  calc.getSecondNumber() + "=" + calc.getLastResult());
                break;
            case "+":
                calc.setLastResult(String.valueOf(calc.getFirstNumber() + calc.getSecondNumber()));
                calc.setHistory(calc.getHistory() + "+" +  calc.getSecondNumber() + "=" + calc.getLastResult());
                break;
            case "-":
                calc.setLastResult(String.valueOf(calc.getFirstNumber() - calc.getSecondNumber()));
                calc.setHistory(calc.getHistory() + "-" +  calc.getSecondNumber() + "=" + calc.getLastResult());
                break;
        }

        tv_result.setText(calc.getLastResult());
        Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
        calc.setFirstNumber(0);
        calc.setSecondNumber(0);
        calc.setCurrentOperation("=");
        Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
        tv_History.setText(calc.getHistory());
    }

    public void operation2(String str) {

        if (checkByNull(String.valueOf(tv_result.getText()))) {
            Toast.makeText(MainActivity.this, "Сначала нужно ввести число", Toast.LENGTH_SHORT).show();
        } else if (String.valueOf(calc.getFirstNumber()).equals("0.0")){
            calc.setFirstNumber(Double.parseDouble(String.valueOf(tv_result.getText())));

            switch (str) {
                case "/":
                    calc.setCurrentOperation("/");
                    break;
                case "*":
                    calc.setCurrentOperation("*");
                    break;
                case "+":
                    calc.setCurrentOperation("+");
                    break;
                case "-":
                    calc.setCurrentOperation("-");
                    break;
            }

            tv_result.setText("");
            Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(MainActivity.this, "????!!!!", Toast.LENGTH_LONG).show();
        }
        tv_History.setText(calc.getHistory());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_0:

                btnSymbolsWork("0");
                break;
            case R.id.btn_1:

                btnSymbolsWork("1");
                break;
            case R.id.btn_2:

                btnSymbolsWork("2");
                break;
            case R.id.btn_3:

                btnSymbolsWork("3");
                break;
            case R.id.btn_4:

                btnSymbolsWork("4");
                break;
            case R.id.btn_5:

                btnSymbolsWork("5");
                break;
            case R.id.btn_6:

                btnSymbolsWork("6");
                break;
            case R.id.btn_7:

                btnSymbolsWork("7");
                break;
            case R.id.btn_8:

                btnSymbolsWork("8");
                break;
            case R.id.btn_9:

                btnSymbolsWork("9");
                break;
            case R.id.btn_dot:

                btnSymbolsWork(".");
                break;
            case R.id.btn_div:

                operation2("/");
                break;
            case R.id.btn_mult:

                operation2("*");
                break;
            case R.id.btn_subtr:

                operation2("-");
                break;
            case R.id.btn_add:

                operation2("+");
                break;
            case R.id.btn_result:

                if (!String.valueOf(calc.getFirstNumber()).equals("0.0") && !tv_result.getText().equals("")) {

                    switch (calc.getCurrentOperation()) {
                        case "/":
                            operation1("/");
                            break;
                        case "*":
                            operation1("*");
                            break;
                        case "+":
                            operation1("+");
                            break;
                        case "-":
                            operation1("-");
                            break;
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Чтобы был результат надо сначала придумать что с чем взаимодействовать будет", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_clear:
                tv_result.setText("");
                calc.setFirstNumber(0);
                calc.setSecondNumber(0);
                calc.setHistory("");
                calc.setLastResult("");
                calc.setCurrentOperation("");
                tv_History.setText("");
                Toast.makeText(MainActivity.this, calc.toString(), Toast.LENGTH_LONG).show();
                break;
        }
    }
}

/*
L3:
1. Напишите обработку каждой кнопки из макета калькулятора.

2. Создайте объект с данными и операциями калькулятора.
    Продумайте, каким образом будете хранить введённые пользователем данные.

3. * Создайте макет калькулятора для горизонтальной ориентации экрана и отображайте его в ландшафтной ориентации.
 */

/*
L2:
1. С этого урока будем писать приложение «Калькулятор».
    Выберите макет для работы с калькулятором.
    Обоснуйте, почему будете использовать именно этот тип макета.

2. Сверстайте главный экран калькулятора.
    На нём должны быть кнопки, обозначающие цифры и знаки действия: «Плюс», «Умножить», «Разделить», «Вычесть» и т. п.

3. * Добавьте фоновый рисунок для экрана калькулятора.
    Следите, чтобы рисунок был для общего использования. Ресурсы: PxHere, Pixabay.
 */