package ar.com.profebot.Models;

import android.app.AlertDialog;
import android.widget.Button;

import ar.com.profebot.service.RVMultipleChoiceAdapter;

public class MultipleChoiceStep {

    private String equationBase;
    private String newEquationBase;
    private String newRegularEquationBase1;
    private String newRegularEquationBase2;
    private String summary;

    // Prosa de la opción A
    private String optionA;
    // Ecuación de la opción A. Ejemplo: 1+1/2=2x+3
    private String equationOptionA;

    // Prosa de la opción B
    private String optionB;
    // Ecuación de la opción B. Ejemplo: 1+1/2=2x+3
    private String equationOptionB;

    // Prosa de la opción C
    private String optionC;
    // Ecuación de la opción C. Ejemplo: 1+1/2=2x+3
    private String equationOptionC;

    private Integer correctOption;
    private Integer chosenOption;
    private Integer regularOption1;
    private Integer regularOption2;
    private String correctOptionJustification;
    private String incorrectOptionJustification1;
    private String incorrectOptionJustification2;
    private Boolean isSolved = false;
    private Boolean nextStepButtonWasPressed = false;
    private Boolean isExpanded = false;
    private RVMultipleChoiceAdapter.MultipleChoiceViewHolder multipleChoiceViewHolder;
    private AlertDialog dialog;
    private Button closeDialog;

    public MultipleChoiceStep(String equationBase, String newEquationBase, String newRegularEquationBase1, String newRegularEquationBase2,
                              String summary, String optionA, String equationOptionA, String optionB, String equationOptionB,
                              String optionC, String equationOptionC, Integer correctOption,
                              Integer regularOption1, Integer regularOption2, String correctOptionJustification,
                              String incorrectOptionJustification1, String incorrectOptionJustification2) {
        this.equationBase = equationBase;
        this.newEquationBase = newEquationBase;
        this.newRegularEquationBase1 = newRegularEquationBase1;
        this.newRegularEquationBase2 = newRegularEquationBase2;
        this.summary = summary;
        this.optionA = optionA;
        this.equationOptionA = equationOptionA;
        this.optionB = optionB;
        this.equationOptionB = equationOptionB;
        this.optionC = optionC;
        this.equationOptionC = equationOptionC;
        this.correctOption = correctOption;
        this.regularOption1 = regularOption1;
        this.regularOption2 = regularOption2;
        this.correctOptionJustification = correctOptionJustification;
        this.incorrectOptionJustification1 = incorrectOptionJustification1;
        this.incorrectOptionJustification2 = incorrectOptionJustification2;
        this.isSolved = false;
    }

    public MultipleChoiceStep(String equationBase, String newEquationBase, String summary, String optionA, String equationOptionA,
                              String optionB, String equationOptionB, String optionC, String equationOptionC,
                              Integer correctOption, String correctOptionJustification, String incorrectOptionJustification1,
                              String incorrectOptionJustification2) {
        this(equationBase, newEquationBase,  null, null, summary,  optionA,  equationOptionA, optionB,  equationOptionB,  optionC,
                equationOptionC, correctOption, null, null,
                correctOptionJustification, incorrectOptionJustification1, incorrectOptionJustification2);
    }

    public Button getCloseDialog() {
        return closeDialog;
    }

    public void setCloseDialog(Button closeDialog) {
        this.closeDialog = closeDialog;
    }

    public AlertDialog getDialog() {
        return dialog;
    }

    public void setDialog(AlertDialog dialog) {
        this.dialog = dialog;
    }

    public RVMultipleChoiceAdapter.MultipleChoiceViewHolder getMultipleChoiceViewHolder() {
        return multipleChoiceViewHolder;
    }

    public void setMultipleChoiceViewHolder(RVMultipleChoiceAdapter.MultipleChoiceViewHolder multipleChoiceViewHolder) {
        this.multipleChoiceViewHolder = multipleChoiceViewHolder;
    }

    public Integer getChosenOption() {
        return chosenOption;
    }

    public void setChosenOption(Integer chosenOption) {
        this.chosenOption = chosenOption;
    }

    public Boolean getExpanded() {
        return isExpanded;
    }

    public void setExpanded(Boolean expanded) {
        isExpanded = expanded;
    }

    public Boolean getNextStepButtonWasPressed() {
        return nextStepButtonWasPressed;
    }

    public void setNextStepButtonWasPressed(Boolean nextStepButtonWasPressed) {
        this.nextStepButtonWasPressed = nextStepButtonWasPressed;
    }

    public String getEquationBase() {
        return equationBase;
    }

    public String getNewEquationBase() {
        return newEquationBase;
    }

    public String getSummary() {
        return summary;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public Integer getCorrectOption() {
        return correctOption;
    }

    public Integer getRegularOption1() {
        return regularOption1;
    }

    public Integer getRegularOption2() {
        return regularOption2;
    }

    public String getCorrectOptionJustification() {
        return correctOptionJustification;
    }

    public String getIncorrectOptionJustification1() {
        return incorrectOptionJustification1;
    }

    public String getIncorrectOptionJustification2() {
        return incorrectOptionJustification2;
    }

    public Boolean getSolved() {
        return isSolved;
    }

    public void setSolved(Boolean solved) {
        isSolved = solved;
    }

    public String getEquationOptionA() {
        return equationOptionA;
    }

    public void setEquationOptionA(String equationOptionA) {
        this.equationOptionA = equationOptionA;
    }

    public String getEquationOptionB() {
        return equationOptionB;
    }

    public void setEquationOptionB(String equationOptionB) {
        this.equationOptionB = equationOptionB;
    }

    public String getEquationOptionC() {
        return equationOptionC;
    }

    public void setEquationOptionC(String equationOptionC) {
        this.equationOptionC = equationOptionC;
    }
}
