package geekbrains.android.calculator;

import android.os.Parcel;
import android.os.Parcelable;

public class Calculator implements Parcelable {

    private String history;
    private double firstNumber;
    private double secondNumber;
    private String currentOperation;
    private String lastResult;

    public Calculator() {

    }

    public Calculator(String history, double firstNumber, double secondNumber, String currentOperation, String lastResult) {
        this.history = history;
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.currentOperation = currentOperation;
        this.lastResult = lastResult;
    }

    protected Calculator(Parcel in) {
        history = in.readString();
        firstNumber = in.readDouble();
        secondNumber = in.readDouble();
        currentOperation = in.readString();
        lastResult = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public double getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(double firstNumber) {
        this.firstNumber = firstNumber;
    }

    public double getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(double secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(String currentOperation) {
        this.currentOperation = currentOperation;
    }

    public String getLastResult() {
        return lastResult;
    }

    public void setLastResult(String lastResult) {
        this.lastResult = lastResult;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(history);
        parcel.writeDouble(firstNumber);
        parcel.writeDouble(secondNumber);
        parcel.writeString(currentOperation);
        parcel.writeString(lastResult);
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "history = '" + history + '\'' +
                ", firstNumber = " + firstNumber +
                ", secondNumber = " + secondNumber +
                ", currentOperation = '" + currentOperation + '\'' +
                ", lastResult = '" + lastResult + '\'' +
                '}';
    }
}
